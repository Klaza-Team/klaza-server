import { defineStore } from "pinia";
import { UserDiscordTelegramServerDTO, UserDTO } from "src/@types/dtos";

// TODO: Pegar infos do server
export const useUserStore = defineStore("user", {
    state: () => ({
        user: {
            id: 1,
            username: "user 1",
            avatar: "https://i.imgur.com/4Z5j5Zm.png",
            courses: [],
            email: "",
            role: "Aluno",
            globalConfig: {
                use_global: true,
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
        } as UserDTO,
    }),

    getters: {
        getUserDiscordServers(): UserDiscordTelegramServerDTO[] {
            //TODO - Pegar os servidores do discord do usuário
            return [
                {
                    id: "discord_aisudhaiudh9asdhiashd98yu9",
                    name: "Server Discord 1",
                },
            ];
        },
        getUserTelegramServers(): UserDiscordTelegramServerDTO[] {
            //TODO - Pegar os servidores do telegram do usuário
            return [
                {
                    id: "telegram_98yusad98unv08aysdgadfhg",
                    name: "Server Telegram 1",
                },
            ];
        },
        getUserServerByID(state): (id: string, type: "discord" | "telegram") => UserDiscordTelegramServerDTO {
            return (id: string, type: "discord" | "telegram") => {
                // TODO - Pegar o servidor do usuário pelo id
                if (type === "discord") {
                    return {
                        id: "discord_aisudhaiudh9asdhiashd98yu9",
                        name: "Server Discord 1",
                    };
                }
                return {
                    id: "telegram_98yusad98unv08aysdgadfhg",
                    name: "Server Telegram 1",
                };   
            }
        }
    },

    actions: {},
});
