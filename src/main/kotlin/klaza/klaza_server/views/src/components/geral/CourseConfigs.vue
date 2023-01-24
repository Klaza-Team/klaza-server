<template>
    <div class="column justify-center gap-15">
        <div class="row justify-center">
            <div v-if="type != 'geral'" class="text-w row justify-between items-center" style="width: 370px;">
                <span>Usar configuração global para esse curso</span>
                <q-toggle
                    v-model="localConfig['use_global']"
                    color="bk"
                    checked-icon="fa-solid fa-check"
                    unchecked-icon="fa-solid fa-times"
                    :disable="type == 'discord_other' || type == 'telegram_other'"
                />
            </div>
        </div>
        <div class="row justify-center gap-15">
            <fieldset class="config-group">
            <legend>Conteudo</legend>

            <div
                class="full-width row justify-between items-center text-w"
            >
                <span class="col-grow"
                    >Notificar criação de conteudo</span
                >
                <q-toggle
                    v-model="localConfig['notify_create_content']"
                    color="bk"
                    checked-icon="fa-solid fa-check"
                    unchecked-icon="fa-solid fa-times"
                    :disable="disabled"
                />
            </div>

            <div
                class="full-width row justify-between items-center text-w"
            >
                <span>Notificar edição de conteudo</span>
                <q-toggle
                    v-model="localConfig['notify_edit_content']"
                    color="bk"
                    checked-icon="fa-solid fa-check"
                    unchecked-icon="fa-solid fa-times"
                    :disable="disabled"
                />
            </div>

            <div
                class="full-width row justify-between items-center text-w"
            >
                <span>Notificar exclusão de conteudo</span>
                <q-toggle
                    v-model="localConfig['notify_delete_content']"
                    color="bk"
                    checked-icon="fa-solid fa-check"
                    unchecked-icon="fa-solid fa-times"
                    :disable="disabled"
                />
            </div>
            </fieldset>
            <fieldset class="config-group">
                <legend>Prazo</legend>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span>Notificar prazo de entraga (2 dias)</span>
                    <q-toggle
                        v-model="localConfig['notify_deadline_2_days']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span>Notificar prazo de entraga (1 dia)</span>
                    <q-toggle
                        v-model="localConfig['notify_deadline_1_day']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span>Notificar prazo de entraga (no dia)</span>
                    <q-toggle
                        v-model="localConfig['notify_deadline']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span>Notificar envio de entraga</span>
                    <q-toggle
                        v-model="localConfig['notify_send_assignment']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>
            </fieldset>
            <fieldset class="config-group">
                <legend>Mensagens e Comentarios</legend>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span>Notificar recebimento de mensagens</span>
                    <q-toggle
                        v-model="localConfig['notify_receive_message']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span
                        >Notificar recebimento de comentarios</span
                    >
                    <q-toggle
                        v-model="localConfig['notify_receive_comment']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>

                <div
                    class="full-width row justify-between items-center text-w"
                >
                    <span>Notificar exclusão de comentarios</span>
                    <q-toggle
                        v-model="localConfig['notify_delete_comment']"
                        color="bk"
                        checked-icon="fa-solid fa-check"
                        unchecked-icon="fa-solid fa-times"
                        :disable="disabled"
                    />
                </div>
            </fieldset>
        </div>
        <div class="row justify-center">
            <q-btn color="a" label="Salvar" :loading="loading" @click="save" />
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import { Course, UserCourseDiscordConfig, UserCourseTelegramConfig, CourseConfig, GlobalConfig } from "src/@types/models.js";
import { useUserStore } from "stores/user"
import { useCoursesStore } from "stores/courses"

export default defineComponent({
    name: "CourseConfigs",
    props: {
        course: {
            type: Object as () => Course,
            required: false,
        },
        type: {
            type: String as () => "user" | "discord_user" | "discord_other" | "telegram_user" | "telegram_other" | "new" | "geral",
            required: true,
        },
        instance: {
            type: Object as () => UserCourseDiscordConfig | UserCourseTelegramConfig,
            required: false,
        },
        loading: {
            type: Boolean,
            required: true,
        },
    },
    data() {
        return {
            localConfig: (
                (this.type === "user") ? { ...this.course?.user_config } : 
                (this.type === "discord_user" || this.type === "discord_other" || this.type === "telegram_user" || this.type === "telegram_other") ? { ...this.instance?.config } :
                (this.type === "new") ? {
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
                    notify_delete_comment: false,
                } as CourseConfig
                : { ...useUserStore().user?.globalConfig }) as CourseConfig,
        };
    },
    methods: {
        save() {
            this.$emit("save", this.localConfig);
        },
    },
    computed: {
        disabled() {
            return (this.type == "geral") ? false : this.localConfig['use_global'] || this.type == 'discord_other' || this.type == 'telegram_other';
        },
    },
    watch: {
        localConfig: {
            handler() {
                if (this.localConfig.use_global) {
                    this.localConfig = { ...useUserStore().user?.globalConfig as GlobalConfig, use_global: true };
                }
            },
            deep: true,
        },
    },
});

</script>

<style lang="scss" scoped>

.config-group {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border: 1px solid $color-w;
    color: $color-w;
    border-radius: 20px;
    padding: 10px;
    flex-grow: 1;

    legend {
        padding: 0 5px;
    }
}

</style>