import { defineStore } from "pinia";
import { UserDiscordTelegramServer, UserDTO } from "src/@types/dtos";

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
        } as UserDTO,
    }),

    getters: {
        getUserDiscordServers(): UserDiscordTelegramServer[] {
            //TODO - Pegar os servidores do discord do usuário
            return [
                {
                    id: "discord_aisudhaiudh9asdhiashd98yu9",
                    name: "Server Discord 1",
                },
            ];
        },
        getUserTelegramServers(): UserDiscordTelegramServer[] {
            //TODO - Pegar os servidores do telegram do usuário
            return [
                {
                    id: "telegram_98yusad98unv08aysdgadfhg",
                    name: "Server Telegram 1",
                },
            ];
        },
    },

    actions: {},
});
