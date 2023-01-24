<template>
    <modal-template 
        v-model="show" 
        title="Criar configuração de servidor"
        type="modal_create"
        >
        <div class="row justify-center">
            <div class="selects-container q-gutter-y-md">
                <q-select
                    v-model="localCourse"
                    label="Curso"
                    dropdown-icon="fa-solid fa-caret-down"
                    class="input"
                    color="a"
                    label-color="w"
                    option-label="fullName"
                    :options="courses"
                    outlined
                />
                <q-select
                    v-model="localType"
                    label="Tipo de servidor"
                    dropdown-icon="fa-solid fa-caret-down"
                    class="input"
                    color="a"
                    label-color="w"
                    option-label="name"
                    :options="types"
                    outlined
                />
                <q-select
                    v-model="localServer"
                    label="Servidor"
                    dropdown-icon="fa-solid fa-caret-down"
                    class="input"
                    color="a"
                    label-color="w"
                    option-label="name"
                    :options="
                        type == 'discord' ? discordServers : telegramServers
                    "
                    :disable="type == null"
                    outlined
                />
            </div>
        </div>

        <div>
            <course-configs :course="(course as Course)" :loading="loading" @save="save" type="new" />
        </div>
    </modal-template>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import ModalTemplate from "src/components/geral/ModalTemplate.vue";
import CourseConfigs from "../geral/CourseConfigs.vue";

import { useUserStore } from "stores/user";
import { useCoursesStore } from "stores/courses";
import { Course, CourseConfig, UserDiscordTelegramServer } from "src/@types/models";

export default defineComponent({
    name: "ModalCreateConfigServer",
    components: {
        ModalTemplate,
        CourseConfigs,
    },
    props: {
        value: {
            type: Boolean,
            default: false,
        },
        course: {
            type: Object as () => Course | null,
            default: null,
        },
        type: {
            type: String as () => "discord" | "telegram" | null,
            default: null,
        },
        server: {
            type: Object as () => UserDiscordTelegramServer | null,
            default: null,
        },
    },
    data() {
        return {
            localCourse: this.course,
            localType: this.type
                ? this.type.substring(0, 1).toUpperCase() +
                  this.type.substring(1)
                : null,
            localServer: this.server,

            courses: useCoursesStore().courses,
            types: ["Discord", "Telegram"],
            discordServers: useUserStore().getUserDiscordServers,
            telegramServers: useUserStore().getUserTelegramServers,

            loading: false,
        };
    },
    methods: {
        save(configs: CourseConfig) {
            this.loading = true;
            useCoursesStore().createCourseServerInstance(this.course?.id as number, this.type as "discord", {
                channel_id: this.localServer?.channel as string,
                config: configs,
                creator_id: useUserStore().user?.id as number,
                guild_id: this.localServer?.id as string,
                id: 0,
            })
            .finally(() => {
                this.loading = false;
                this.show = false;
            });
        },
    },
    computed: {
        show: {
            get() {
                return this.value;
            },
            set(value: boolean) {
                this.$emit("input", value);
            },
        },
    },
});
</script>

<style lang="scss" scoped>
.selects-container {
    width: 60%;
}
</style>
