import { defineStore } from 'pinia';
import { useUserStore } from './user';
import { useCoursesStore } from './courses';
import { LocalStorage } from "quasar"

// TODO: Pegar infos do server
export const useLoginStore = defineStore('login', {

	state: () => ({
		login: LocalStorage.getItem("login") || "",
		id: LocalStorage.getItem("id") || 0,
	}),

	getters: {
		isAutheticated(): boolean {
			return this.login !== "" && this.id != 0;
		}
	},

	actions: {
		async authenticate(login: string, password: string): Promise<boolean> {
			// Authenticate user

			return new Promise(async (resolve) => {

				this.login = login;
				this.id = 2;

				LocalStorage.set("login", this.login);
				LocalStorage.set("id", this.id);

				await useUserStore().getUser();
				await useCoursesStore().getCourses();

				resolve(true);

			})

		},
	}

});