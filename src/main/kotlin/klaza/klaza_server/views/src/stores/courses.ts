import { defineStore } from "pinia";
import {
    CourseConfig,
    Course,
    UserCourseDiscordConfig,
    UserCourseTelegramConfig,
    User,
    DiscordInstance,
    TelegramInstance,
} from "src/@types/models";
import { api } from "boot/axios";
import { useUserStore } from "./user";
import { AxiosResponse } from "axios"
import { CourseDTO, DiscordInstanceDTO, TelegramInstanceDTO, UserCourseConfigDTO, UserDTO } from "src/@types/dtos";
import { useLoginStore } from "./login";


// TODO: Pegar infos do server
export const useCoursesStore = defineStore("courses", {
    state: () => ({
        courses: [] as Course[],
    }),

    getters: {
        getCourseById: (state) => {
            return (id: number) =>
                state.courses.find((course) => course.id === id);
        },
        sortCoursesById: (state) => {
            return state.courses.sort((a, b) => a.id - b.id);
        },
        sortCoursesByFullName: (state) => {
            return state.courses.sort((a, b) =>
                a.fullName.localeCompare(b.fullName)
            );
        },
        sortCoursesByActived: (state) => {
            return state.courses.sort((a) => (a.actived ? -1 : 1));
        },
        sortCoursesActivedAndFullName: (state) => {
            return state.courses.sort((a, b) => {
                if (a.actived && !b.actived) {
                    return -1;
                }

                if (!a.actived && b.actived) {
                    return 1;
                }

                return a.fullName.localeCompare(b.fullName);
            });
        },
        sortServerCoursesActived: (state) => {
            return state.courses.sort((a) =>
                a.discordIntances.other.length > 0
                    ? -1
                    : 1 || a.telegramIntances.other.length > 0
                    ? -1
                    : 1
            );
        },
        sortServerCoursesActivedAndFullName: (state) => {
            return state.courses.sort((a, b) => {
                const ADiscord =
                    a.discordIntances.user.length > 0 ||
                    a.discordIntances.other.length > 0;
                const ATelegram =
                    a.telegramIntances.user.length > 0 ||
                    a.telegramIntances.other.length > 0;

                const BDiscord =
                    b.discordIntances.user.length > 0 ||
                    b.discordIntances.other.length > 0;
                const BTelegram =
                    b.telegramIntances.user.length > 0 ||
                    b.telegramIntances.other.length > 0;

                if ((ADiscord || ATelegram) && !(BDiscord || BTelegram)) {
                    return -1;
                }

                if (!(ADiscord || ATelegram) && (BDiscord || BTelegram)) {
                    return 1;
                }

                return a.fullName.localeCompare(b.fullName);
            });
        },
    },

    actions: {

        async getCourses(): Promise<Course[]> {
            
            return new Promise((resolve, reject) => {

                const user: User = useUserStore().user as User;
                const userDTO: UserDTO = {
                    id: user.id,
                    avatar: user.avatar,
                    email: user.email,
                    username: user.username,
                    global_config: user.globalConfig,
                    notification_priority: user.notification_priority,
                    role: user.role,
                }
            
                api.get(`/users/${user.id}/courses`)
                .then(async (res: AxiosResponse<CourseDTO[]>) => {

                    const courses = res.data
                    const returnCourses: Course[] = [];
                    
                    const usersDTO: { [id: string]: UserDTO } = {}

                    for (const course of courses) {
                        
                        for (const discordInstance of course.discordIntances) {
                            if (!usersDTO[discordInstance.creator_id]) {
                                useUserStore().getUserByID(discordInstance.creator_id)
                                .then(user => { usersDTO[discordInstance.creator_id] = user })
                                .catch((e) => { console.error(e) })
                            }
                        }

                        for (const telegramInstance of course.telegramIntances) {
                            if (!usersDTO[telegramInstance.creator_id]) {
                                useUserStore().getUserByID(telegramInstance.creator_id)
                                .then(user => { usersDTO[telegramInstance.creator_id] = user })
                                .catch((e) => { console.error(e) })
                            }
                        }

                        const DiscordInstances: DiscordInstance = {
                            user: course.discordIntances.filter(instance => instance.creator_id === user.id).map(instance => ({ ...instance, creator: userDTO })),
                            other: course.discordIntances.filter(instance => instance.creator_id !== user.id).map(instance => ({ ...instance, creator: usersDTO[instance.creator_id] })),
                        }

                        const TelegramInstances: TelegramInstance = {
                            user: course.telegramIntances.filter(instance => instance.creator_id === user.id).map(instance => ({ ...instance, creator: userDTO })),
                            other: course.telegramIntances.filter(instance => instance.creator_id !== user.id).map(instance => ({ ...instance, creator: usersDTO[instance.creator_id] })),
                        }

                        await this.getCourseUserConfig(course.id, user.id).then(config => {
                            returnCourses.push({
                                ...course,
                                actived: true,
                                user_config: config,
                                discordIntances: DiscordInstances,
                                telegramIntances: TelegramInstances,
                            });
                        })
                        .catch(() => {
                            returnCourses.push({
                                ...course,
                                actived: false,
                                user_config: {
                                    use_global: true,
                                    notify_create_content: true,
                                    notify_deadline: true,
                                    notify_deadline_1_day: true,
                                    notify_deadline_2_days: true,
                                    notify_delete_comment: true,
                                    notify_delete_content: true,
                                    notify_edit_content: true,
                                    notify_receive_comment: true,
                                    notify_receive_message: true,
                                    notify_send_assignment: true,
                                },
                                discordIntances: DiscordInstances,
                                telegramIntances: TelegramInstances,
                            });
                        })

                    }

                    this.courses = returnCourses;
                    resolve(returnCourses);

                })
                .catch((err) => {
                    console.error(err);
                    reject(err);
                })

            })
            
        },

        async getCourseUserConfig(courseId: number, userId: number): Promise<UserCourseConfigDTO> {

            return new Promise((resolve, reject) => {
                    
                api.get(`/users/${userId}/course_configs/${courseId}`)
                .then((res: AxiosResponse<UserCourseConfigDTO>) => {
                    resolve(res.data);
                })
                .catch((err) => {
                    console.error(err);
                    reject(err);
                })
    
            })

        },

        async updateCourseUserConfig(courseId: number, userId: number, config: UserCourseConfigDTO): Promise<boolean> {
                
            return new Promise((resolve, reject) => {
                    
                api.put(`/users/${userId}/course_configs/${courseId}`, config)
                .then(() => {
                    resolve(true);
                })
                .catch((err) => {
                    console.error(err);
                    reject(err);
                })
    
            })
    
        },

        async removeCourseInstance(courseId: number, instanceId: number, type: 'discord' | 'telegram'): Promise<boolean> {
            return new Promise((resolve, reject) => {
                
                const url = (type == "discord") ? `discordInstance/${instanceId}` : `telegramInstance/${instanceId}`;
                const course = this.courses.find(c => c.id === courseId) as Course;

                api.delete(url)
                .then(() => {
                    resolve(true);
                    if (type == "discord") {
                        course.discordIntances.user = course?.discordIntances.user.filter(i => i.id !== instanceId);
                    }
                    else {
                        course.telegramIntances.user = course?.telegramIntances.user.filter(i => i.id !== instanceId);
                    }
                })
                .catch((err) => {
                    console.error(err);
                    reject(err);
                })
    
            })
        },

        async createCourseServerInstance(courseId: number, type: 'discord' | 'telegram', dto: DiscordInstanceDTO | TelegramInstanceDTO): Promise<boolean> {
            return new Promise((resolve, reject) => {
                    
                const url = (type == "discord") ? `/discordInstance/create/${courseId}/${useUserStore().user?.id}` : `/telegramInstance/create/${courseId}/${useUserStore().user?.id}`;
                const course = this.courses.find(c => c.id === courseId) as Course;

                api.post(url, dto)
                .then(() => {
                    resolve(true);
                    if (type == "discord") {
                        course.discordIntances.user.push({ ...dto as DiscordInstanceDTO, creator: { ...useUserStore().user, global_config: useUserStore().user?.globalConfig } } as UserCourseDiscordConfig);
                    }
                    else {
                        course.telegramIntances.user.push({ ...dto as TelegramInstanceDTO, creator: { ...useUserStore().user, global_config: useUserStore().user?.globalConfig } } as UserCourseTelegramConfig);
                    }
                })
                .catch((err) => {
                    console.error(err);
                    reject(err);
                })
    
            })
        },

        async editCourseServerInstance(courseId: number, type: 'discord' | 'telegram', dto: DiscordInstanceDTO | TelegramInstanceDTO): Promise<boolean> {
            return new Promise((resolve, reject) => {
                    
                const url = (type == "discord") ? `/discordInstance` : `/telegramInstance`;

                api.put(url, dto)
                .then(() => {
                    resolve(true);
                })
                .catch((err) => {
                    console.error(err);
                    reject(err);
                })
    
            })
        }

    },

});
