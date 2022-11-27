import { defineStore } from 'pinia';
import { UserDTO } from 'src/@types/dtos';

// TODO: Pegar infos do server
export const useUserStore = defineStore('user', {
	state: () => ({
		user: {
			id: 1,
			username: "user 1",
			avatar: "https://i.imgur.com/4Z5j5Zm.png",
			courses: [],
			email: "",
			role: "",
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
			}
		} as UserDTO,
	}),

	getters: {},

	actions: {}
});
