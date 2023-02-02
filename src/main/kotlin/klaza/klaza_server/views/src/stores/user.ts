import { defineStore } from "pinia";
import { UserDiscordTelegramServer, User } from "src/@types/models";
import { api } from "boot/axios";
import { useLoginStore } from "./login";
import { UserDTO, UserGlobalConfigDTO, UserNotificationAppDTO } from "src/@types/dtos";
import { AxiosResponse } from "axios"

const user_exemple: User = {
    id: 1,
    username: "user 1",
    avatar: "https://i.imgur.com/4Z5j5Zm.png",
    email: "",
    role: "Aluno",
    globalConfig: {
        notify_create_content: true,
        notify_edit_content: true,
        notify_delete_content: true,
        notify_deadline_2_days: true,
        notify_deadline_1_day: true,
        notify_deadline: true,
        notify_send_assignment: true,
        notify_receive_message: true,
        notify_receive_comment: true,
        notify_delete_comment: true,
    },
    notification_priority: [
        {
            id: 1,
            priority: 2,
            type: "discord",
            value: "123456789",
        },
        {
            id: 2,
            priority: 1,
            type: "telegram",
            value: "123456789",
        },
        {
            id: 3,
            priority: 0,
            type: "whatsapp",
            value: "123456789",
        }
    ]
}


// TODO: Pegar infos do server
export const useUserStore = defineStore("user", {
    state: () => ({
        // user: user_exemple // Mock
        user: null as User | null
    }),

    getters: {
        getUserDiscordServers(): UserDiscordTelegramServer[] {
            return [
                {
                    id: "1016881985161019472",
                    channel: "1016881985161019475",
                    name: "Teste Klaza - geral",
                },
            ];
        },
        getUserTelegramServers(): UserDiscordTelegramServer[] {
            return [
                {
                    id: "telegram_98yusad98unv08aysdgadfhg",
                    channel: "lel",
                    name: "Server Telegram 1",
                },
            ];
        },
        getUserServerByID(state): (id: string, type: "discord" | "telegram") => UserDiscordTelegramServer {
            return (id: string, type: "discord" | "telegram") => {
               
                if (type === "discord") {
                    return {
                        id: "1016881985161019472",
                        channel: "1016881985161019475",
                        name: "Teste Klaza - geral",
                    }
                }
                return {
                    id: "telegram_98yusad98unv08aysdgadfhg",
                    channel: "lel",
                    name: "Server Telegram 1",
                }
                
            }
        }
    },

    actions: {
        async getUser() {
            return new Promise((resolve, reject) => {
                api.get(`/users/${useLoginStore().id}`)
                .then((res: AxiosResponse<UserDTO>) => {
                    
                    const returnUser: User = { ...res.data, globalConfig: res.data.global_config  } as User
                    
                    if (!returnUser.notification_priority.find((el) => el.type === "discord")) {
                        returnUser.notification_priority.push({
                            id: 0,
                            priority: -1,
                            type: "discord",
                            value: "",
                        })
                    }

                    if (!returnUser.notification_priority.find((el) => el.type === "telegram")) {
                        returnUser.notification_priority.push({
                            id: 0,
                            priority: -1,
                            type: "telegram",
                            value: "",
                        })
                    }

                    if (!returnUser.notification_priority.find((el) => el.type === "whatsapp")) {
                        returnUser.notification_priority.push({
                            id: 0,
                            priority: -1,
                            type: "whatsapp",
                            value: "",
                        })
                    }

                    this.user = returnUser
                    resolve(returnUser)

                })
                .catch((err) => {
                    console.log(err)
                    reject(err)
                })
            })
        },
        async getUserByID(id: number): Promise<UserDTO> {
            return new Promise((resolve, reject) => {
                api.get(`/users/${id}`)
                .then((res: AxiosResponse<UserDTO>) => {
                    resolve(res.data)
                })
                .catch((err) => {
                    console.log(err)
                    reject(err)
                })
            })
        },
        async editGlobalConfig(config: UserGlobalConfigDTO): Promise<boolean> {
            return new Promise((resolve, reject) => {
                api.put(`/users/${useLoginStore().id}/global_config`, config)
                .then(() => {
                    resolve(true)
                })
                .catch((err) => {
                    console.log(err)
                    reject(err)
                })
            })
        },
        async loginDiscord(): Promise<boolean> {
            return new Promise((resolve, reject) => {

                const demo: UserNotificationAppDTO = {
                    id: 0,
                    priority: parseInt(process.env.DEMO_DISCORD_PRIORITY),
                    type: "discord",
                    value: process.env.DEMO_DISCORD_ACCOUNT,
                }

                api.put(`/users/${useLoginStore().id}/discordAccount`, demo)
                .then(() => {

                    const discord = this.user?.notification_priority.findIndex((el) => el.type === "discord") as number
                    
                    if (discord !== -1) {
                        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                        //@ts-ignore
                        useUserStore().user?.notification_priority.splice(discord, 1, demo)
                    }

                    resolve(true)
                })
                .catch((err) => {
                    console.log(err)
                    reject(err)
                })
            })
        },
        async loginTelegram(): Promise<boolean> {
            return new Promise((resolve, reject) => {

                const demo: UserNotificationAppDTO = {
                    id: 0,
                    priority: parseInt(process.env.DEMO_TELEGRAM_PRIORITY),
                    type: "telegram",
                    value: process.env.DEMO_TELEGRAM_ACCOUNT,
                }

                api.put(`/users/${useLoginStore().id}/telegramAccount`, demo)
                .then(() => {

                    const telegram = this.user?.notification_priority.findIndex((el) => el.type === "telegram") as number
                    
                    if (telegram !== -1) {
                        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                        //@ts-ignore
                        useUserStore().user?.notification_priority.splice(telegram, 1, demo)
                    }

                    resolve(true)
                })
                .catch((err) => {
                    console.log(err)
                    reject(err)
                })
            })
        },
        async loginWhatsapp(): Promise<boolean> {
            return new Promise((resolve, reject) => {

                const demo: UserNotificationAppDTO = {
                    id: 0,
                    priority: parseInt(process.env.DEMO_WHATSAPP_PRIORITY),
                    type: "whatsapp",
                    value: process.env.DEMO_WHATSAPP_ACCOUNT,
                }

                api.put(`/users/${useLoginStore().id}/whatsappAccount`, demo)
                .then(() => {

                    const whatsapp = this.user?.notification_priority.findIndex((el) => el.type === "whatsapp") as number
                    
                    if (whatsapp !== -1) {
                        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                        //@ts-ignore
                        useUserStore().user?.notification_priority.splice(whatsapp, 1, demo)
                    }

                    resolve(true)
                })
                .catch((err) => {
                    console.log(err)
                    reject(err)
                })
            })
        }
    },
});
