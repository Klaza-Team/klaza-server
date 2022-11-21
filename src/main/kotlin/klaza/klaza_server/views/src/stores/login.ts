import { defineStore } from 'pinia';

// TODO: Pegar infos do server
export const useLoginStore = defineStore('login', {

	state: () => ({
		login: "",
	}),

	getters: {
		isAutheticated(): boolean {
			return this.login !== "";
		}
	},

	actions: {
		authenticate(login: string, password: string): boolean {
			// Authenticate user

			this.login = login;

			return true;

		}
	}

});