<template>
    <q-item class="justify-center">
        <div>
            <q-btn @click="login" no-caps>
                <div class="q-mr-sm">
                    <q-icon name="fa-solid fa-up-down" size="15px" />
                </div>

                <div class="q-mr-sm row itens-center">
                    <template v-if="account.type == 'discord'">
                        <svg
                            width="30"
                            height="25"
                            viewBox="0 0 31 35"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                            class="text-w"
                        >
                            <path
                                d="M20.3175 16.625C20.3175 17.6925 19.53 18.5675 18.5325 18.5675C17.5525 18.5675 16.7475 17.6925 16.7475 16.625C16.7475 15.5575 17.535 14.6825 18.5325 14.6825C19.53 14.6825 20.3175 15.5575 20.3175 16.625ZM12.145 14.6825C11.1475 14.6825 10.36 15.5575 10.36 16.625C10.36 17.6925 11.165 18.5675 12.145 18.5675C13.1425 18.5675 13.93 17.6925 13.93 16.625C13.9475 15.5575 13.1425 14.6825 12.145 14.6825ZM30.625 3.605V35C26.2162 31.1039 27.6262 32.3936 22.505 27.6325L23.4325 30.87H3.5875C1.61 30.87 0 29.26 0 27.265V3.605C0 1.61 1.61 0 3.5875 0H27.0375C29.015 0 30.625 1.61 30.625 3.605ZM25.6375 20.195C25.6375 14.56 23.1175 9.9925 23.1175 9.9925C20.5975 8.1025 18.2 8.155 18.2 8.155L17.955 8.435C20.93 9.345 22.3125 10.6575 22.3125 10.6575C18.1555 8.37915 13.2724 8.37874 9.24 10.15C8.5925 10.4475 8.2075 10.6575 8.2075 10.6575C8.2075 10.6575 9.66 9.275 12.81 8.365L12.635 8.155C12.635 8.155 10.2375 8.1025 7.7175 9.9925C7.7175 9.9925 5.1975 14.56 5.1975 20.195C5.1975 20.195 6.6675 22.7325 10.535 22.855C10.535 22.855 11.1825 22.0675 11.7075 21.4025C9.485 20.7375 8.645 19.3375 8.645 19.3375C8.90244 19.5177 9.32695 19.7513 9.3625 19.775C12.3163 21.4292 16.5121 21.9711 20.2825 20.3875C20.895 20.16 21.5775 19.8275 22.295 19.355C22.295 19.355 21.42 20.79 19.1275 21.4375C19.6525 22.1025 20.2825 22.855 20.2825 22.855C24.15 22.7325 25.6375 20.195 25.6375 20.195Z"
                                fill="currentColor"
                            />
                        </svg>

                        <span class="q-ml-xs">
                            {{
                                account.value != "" ? "Conectado" : "Entrar com Discord"
                            }}
                        </span>
                    </template>

                    <template v-if="account.type == 'telegram'">
                        <svg
                            width="30"
                            height="25"
                            viewBox="0 0 43 35"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                            class="text-w"
                        >
                            <path
                                d="M42.2481 3.18629L35.9502 32.8872C35.4751 34.9835 34.236 35.5052 32.4752 34.5176L22.8792 27.4464L18.2489 31.8997C17.7365 32.4121 17.3079 32.8407 16.3204 32.8407L17.0098 23.0677L34.795 6.99674C35.5682 6.30732 34.6273 5.92534 33.5931 6.61476L11.6062 20.4591L2.14068 17.4964C0.0817406 16.8536 0.0444745 15.4375 2.56924 14.4499L39.593 0.186385C41.3072 -0.456452 42.8071 0.568361 42.2481 3.18629Z"
                                fill="currentColor"
                            />
                        </svg>

                        <span class="q-ml-xs">
                            {{
                                account.value != ""
                                    ? "Conectado"
                                    : "Entrar com Telegram"
                            }}
                        </span>
                    </template>

                    <template v-if="account.type == 'whatsapp'">
                        <svg
                            width="31"
                            height="25"
                            viewBox="0 0 36 35"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                            class="text-w"
                        >
                            <path
                                d="M30.2578 5.08594C26.9844 1.80469 22.625 0 17.9922 0C8.42969 0 0.648438 7.78125 0.648438 17.3438C0.648438 20.3984 1.44531 23.3828 2.96094 26.0156L0.5 35L9.69531 32.5859C12.2266 33.9688 15.0781 34.6953 17.9844 34.6953H17.9922C27.5469 34.6953 35.5 26.9141 35.5 17.3516C35.5 12.7188 33.5312 8.36719 30.2578 5.08594ZM17.9922 31.7734C15.3984 31.7734 12.8594 31.0781 10.6484 29.7656L10.125 29.4531L4.67188 30.8828L6.125 25.5625L5.78125 25.0156C4.33594 22.7188 3.57812 20.0703 3.57812 17.3438C3.57812 9.39844 10.0469 2.92969 18 2.92969C21.8516 2.92969 25.4688 4.42969 28.1875 7.15625C30.9062 9.88281 32.5781 13.5 32.5703 17.3516C32.5703 25.3047 25.9375 31.7734 17.9922 31.7734ZM25.8984 20.9766C25.4688 20.7578 23.3359 19.7109 22.9375 19.5703C22.5391 19.4219 22.25 19.3516 21.9609 19.7891C21.6719 20.2266 20.8438 21.1953 20.5859 21.4922C20.3359 21.7812 20.0781 21.8203 19.6484 21.6016C17.1016 20.3281 15.4297 19.3281 13.75 16.4453C13.3047 15.6797 14.1953 15.7344 15.0234 14.0781C15.1641 13.7891 15.0937 13.5391 14.9844 13.3203C14.875 13.1016 14.0078 10.9688 13.6484 10.1016C13.2969 9.25781 12.9375 9.375 12.6719 9.35938C12.4219 9.34375 12.1328 9.34375 11.8438 9.34375C11.5547 9.34375 11.0859 9.45312 10.6875 9.88281C10.2891 10.3203 9.17188 11.3672 9.17188 13.5C9.17188 15.6328 10.7266 17.6953 10.9375 17.9844C11.1562 18.2734 13.9922 22.6484 18.3438 24.5312C21.0938 25.7188 22.1719 25.8203 23.5469 25.6172C24.3828 25.4922 26.1094 24.5703 26.4688 23.5547C26.8281 22.5391 26.8281 21.6719 26.7188 21.4922C26.6172 21.2969 26.3281 21.1875 25.8984 20.9766Z"
                                fill="currentColor"
                            />
                        </svg>

                        <span class="q-ml-xs">
                            {{
                                account.value != ""
                                    ? "Conectado"
                                    : "Entrar com WhatsApp"
                            }}
                        </span>
                    </template>
                </div>

                <div>
                    <q-toggle 
                        color="w"
                        v-model="enabled"
                        :disable="account.value == ''"
                    />
                </div>
            </q-btn>
        </div>

        <q-tooltip class="tooltip">
            <span> {{ tooltipText }} </span>
        </q-tooltip>

    </q-item>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import { UserNotificationApp } from "src/@types/models.js";
import { useUserStore } from "src/stores/user";

export default defineComponent({
    name: "AccountItem",
    emits: ["update-priority", "login-whatsapp"],
    props: {
        account: {
            type: Object as () => UserNotificationApp,
            required: true,
        },
        index: {
            type: Number,
            required: true,
        },
    },
    computed: {
        enabled: {
            get() {
                return this.account.priority >= 0
            },
            set(value: boolean) {
                this.$emit("update-priority", {
                    ...this.account,
                    priority: value ? 0 : -1,
                    index: this.index,
                });
            },
        },
        tooltipText() {
            return this.account.value != "" ? 
                (this.account.type == "discord") ? `Conectado como ManoGamer007#2761` :
                (this.account.type == "telegram") ? `Conectado como @manogamer` :
                `Conectado como +55 51 9280-4789` 
                : "Clique para conectar";
        },
    },
    methods: {
        login() {
            switch (this.account.type) {
                case "discord":
                    this.loginDiscord();
                    break;
                case "telegram":
                    this.loginTelegram();
                    break;
                case "whatsapp":
                    this.loginWhatsapp();
                    break;
            }
        },
        loginDiscord() {
            window.open(
                "https://discord.com/api/oauth2/authorize?client_id=1016881283986632764&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fklaza%2Fperfil&response_type=code&scope=identify",
                "Discord Login",
                "location=no,height=700,width=690,popup=yes,resizable=no"
            )
            
            useUserStore().loginDiscord();

            this.$forceUpdate();

        },
        loginTelegram() {
           
            window.open(
                "https://oauth.telegram.org/auth?bot_id=531675494&origin=https%3A%2F%2Ftelegram.org&embed=1&request_access=write&return_to=https%3A%2F%2Ftelegram.org%2Fblog%2Flogin",
                "Telegram Login",
                "location=no,height=700,width=690,popup=yes,resizable=no"
            )

            useUserStore().loginTelegram();

            this.$forceUpdate();
            
        },
        loginWhatsapp() {
            
            this.$emit("login-whatsapp");

            useUserStore().loginWhatsapp();

            this.$forceUpdate();

        },
    }
});
</script>

<style lang="scss" scoped>
.q-btn {

    display: flex;
    align-items: center;

    padding: 5px 25px;
    background-color: $color-a;
    border-radius: 50px;
    width: fit-content;
}

</style>
