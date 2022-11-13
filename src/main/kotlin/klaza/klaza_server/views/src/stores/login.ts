import { defineStore } from 'pinia';

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