import { defineStore } from "pinia";
import { CourseConfigDTO, CourseDTO, UserCourseDiscordConfigDTO, UserCourseTelegramConfigDTO, UserDTO } from "src/@types/dtos";

const usersExemple: UserDTO[] = [
    {
        id: 1,
        username: "user 1",
        avatar: "https://i.imgur.com/4Z5j5Zm.png",
        courses: [],
        email: "",
        role: ""
    },
    {
        id: 2,
        username: "user 2",
        avatar: "https://i.imgur.com/4Z5j5Zm.png",
        courses: [],
        email: "",
        role: ""
    }
]

const userConfigExemple: CourseConfigDTO[] = [
    {
        use_global: false,
        notify_create_content: false,
        notify_edit_content: false,
        notify_delete_content: false,
        notify_deadline_2_days: false,
        notify_deadline_1_day: false,
        notify_deadline: false,
        notify_send_assignment: false,
        notify_receive_message: false,
        notify_receive_comment: false,
        notify_delete_comment: false
    },
    {
        use_global: true,
        notify_create_content: false,
        notify_edit_content: false,
        notify_delete_content: false,
        notify_deadline_2_days: false,
        notify_deadline_1_day: false,
        notify_deadline: false,
        notify_send_assignment: false,
        notify_receive_message: false,
        notify_receive_comment: false,
        notify_delete_comment: false
    },
    {
        use_global: false,
        notify_create_content: true,
        notify_edit_content: false,
        notify_delete_content: true,
        notify_deadline_2_days: false,
        notify_deadline_1_day: false,
        notify_deadline: true,
        notify_send_assignment: false,
        notify_receive_message: true,
        notify_receive_comment: true,
        notify_delete_comment: true
    }
]

const discordIntancesExemple: UserCourseDiscordConfigDTO[] = [
    {
        id: 1,
        channel_id: "123",
        guild_id: "123",
        config: userConfigExemple[0],
        creator: usersExemple[0]
    },
    {
        id: 2,
        channel_id: "a586s4da98sd7a6s12d36540",
        guild_id: "a6s5d4a968sd49",
        config: userConfigExemple[2],
        creator: usersExemple[1]
    },
    {
        id: 3,
        channel_id: "65as4d6a312sd06a84d1032asd495",
        guild_id: "58as7d6a21da698sd41a32sd1918",
        config: userConfigExemple[1],
        creator: usersExemple[2]
    }

]

const telegramIntancesExemple: UserCourseTelegramConfigDTO[] = [
    {
        id: 1,
        channel_id: "z0xc.2as64d2a1sd98a841d30axc65as541da32sd4",
        config: userConfigExemple[0],
        creator: usersExemple[0]
    },
    {
        id: 2,
        channel_id: "0xc0.1c65x5c49s4d10asd6354as6d51",
        config: userConfigExemple[2],
        creator: usersExemple[1]
    },
    {
        id: 3,
        channel_id: "2sa0d3a2sd65a45sd986a512sd234",
        config: userConfigExemple[1],
        creator: usersExemple[2]
    }

]

const courses_exemple: CourseDTO[] = [

    {
        id: 3,
        fullName: "Introdução a inteligência artificial",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: true,
        user_config: userConfigExemple[0],
        discordIntances: {
            user: [ discordIntancesExemple[0], discordIntancesExemple[2] ],
            other: [ discordIntancesExemple[1], discordIntancesExemple[2] ]
        },
        telegramIntances: {
            user: [ telegramIntancesExemple[0], telegramIntancesExemple[2] ],
            other: [ telegramIntancesExemple[1], telegramIntancesExemple[2] ]
        }
    },
    {
        id: 6,
        fullName:
            "Curso Técnico em Informática e Curso Técnico em desenvolvimento de sistemas",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: false,
        user_config: userConfigExemple[1],
        discordIntances: {
            user: [],
            other: []
        },
        telegramIntances: {
            user: [],
            other: []
        },
    },
    {
        id: 1,
        fullName: "Seminario para TCC",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: true,
        user_config: userConfigExemple[2],
        discordIntances: {
            user: [],
            other: []
        },
        telegramIntances: {
            user: [],
            other: []
        },
    },
    {
        id: 4,
        fullName: "Engenharia de Software",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: false,
        user_config: userConfigExemple[0],
        discordIntances: {
            user: [ discordIntancesExemple[0], discordIntancesExemple[2] ],
            other: [ discordIntancesExemple[1], discordIntancesExemple[2] ]
        },
        telegramIntances: {
            user: [],
            other: []
        },
    },
    {
        id: 2,
        fullName: "Empreendedorismo",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: true,
        user_config: userConfigExemple[2],
        discordIntances: {
            user: [],
            other: []
        },
        telegramIntances: {
            user: [],
            other: []
        },
    },
    {
        id: 5,
        fullName: "Introdução a burrice artificial",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: false,
        user_config: userConfigExemple[1],
        discordIntances: {
            user: [],
            other: []
        },
        telegramIntances: {
            user: [],
            other: []
        },
    },
    {
        id: 8,
        fullName: "Introdução a Introdução de inteligência artificial",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: false,
        user_config: userConfigExemple[0],
        discordIntances: {
            user: [],
            other: [ discordIntancesExemple[0], discordIntancesExemple[1], discordIntancesExemple[2] ]
        },
        telegramIntances: {
            user: [ telegramIntancesExemple[0], telegramIntancesExemple[1], telegramIntancesExemple[2] ],
            other: []
        },
    },
    {
        id: 7,
        fullName: "Projeto de Sistemas",
        shortName: "",
        image: "https://i.imgur.com/4Z5j5Zm.png",
        actived: false,
        user_config: userConfigExemple[2],
        discordIntances: {
            user: [],
            other: []
        },
        telegramIntances: {
            user: [ telegramIntancesExemple[0] ],
            other: [ telegramIntancesExemple[1], telegramIntancesExemple[2] ]
        },
    },

]

// TODO: Pegar infos do server
export const useCoursesStore = defineStore("courses", {
    state: () => ({
        courses: courses_exemple,
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
            return state.courses.sort((a) => (a.discordIntances.other.length > 0 ? -1 : 1 || a.telegramIntances.other.length > 0 ? -1 : 1));
        },
        sortServerCoursesActivedAndFullName: (state) => {

            return state.courses.sort((a, b) => {

                const ADiscord = a.discordIntances.user.length > 0 || a.discordIntances.other.length > 0
                const ATelegram = a.telegramIntances.user.length > 0 || a.telegramIntances.other.length > 0

                const BDiscord = b.discordIntances.user.length > 0 || b.discordIntances.other.length > 0
                const BTelegram = b.telegramIntances.user.length > 0 || b.telegramIntances.other.length > 0

                if ((ADiscord || ATelegram) && !(BDiscord || BTelegram)) {
                    return -1;
                }

                if (!(ADiscord || ATelegram) && (BDiscord || BTelegram)) {
                    return 1;
                }

                return a.fullName.localeCompare(b.fullName);
            });
        }
    },

    actions: {},
});
