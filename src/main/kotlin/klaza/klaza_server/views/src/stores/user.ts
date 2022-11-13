import { defineStore } from 'pinia';

// TODO: Pegar infos do server
export const useUserStore = defineStore('user', {
    state: () => ({
      user: {},
    }),

    getters: {},

    actions: {}
});
