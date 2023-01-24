<template>
    <q-expansion-item
        expand-icon="fa-solid fa-chevron-right"
        expanded-icon="fa-solid fa-chevron-down"
        expand-icon-class="text-bk"
        class="instance-item bg-c"
        expand-separator
        switch-toggle-side
    >
        <template v-slot:header>
            <div class="fit row no-wrap items-center">
                <span class="text-bk text-ellipsis col">
                    {{ course.fullName }}
                </span>
                <span class="text-bk text-ellipsis col">
                    {{ instance.channel_id }}
                </span>
                <span class="text-bk text-ellipsis col">
                    {{ instance.creator.username }}</span
                >

                <div v-if="type === 'discord_user' || type === 'telegram_user'">
                    <q-btn
                        color="bk"
                        icon="fa-solid fa-trash-can"
                        size="md"
                        @click="remove"
                        :loading="loading"
                        flat
                    />
                </div>
            </div>
        </template>
        <q-card>
            <q-card-section class="configs">
                <course-configs
                    :course="course"
                    :type="type"
                    :instance="instance"
                    :loading="loading"
                    @save="save"
                />
            </q-card-section>
        </q-card>
    </q-expansion-item>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import {
    Course,
    CourseConfig,
    UserCourseDiscordConfig,
    UserCourseTelegramConfig,
} from "src/@types/models.js";
import CourseConfigs from "src/components/geral/CourseConfigs.vue";
import { useCoursesStore } from "src/stores/courses";
import { useUserStore } from "src/stores/user";

export default defineComponent({
    name: "InstanceItem",
    components: {
        CourseConfigs,
    },
    props: {
        course: {
            type: Object as () => Course,
            required: true,
        },
        instance: {
            type: Object as () =>
                | UserCourseDiscordConfig
                | UserCourseTelegramConfig,
            required: true,
        },
        type: {
            type: String as () =>
                | "discord_user"
                | "discord_other"
                | "telegram_user"
                | "telegram_other",
            required: true,
        },
    },
    data() {
        return {
            loading: false,
        };
    },
    methods: {
        remove() {
            this.loading = true;
            useCoursesStore().removeCourseInstance(this.course.id, this.instance.id, (this.type === "discord_user" || this.type === "discord_other") ? "discord" : "telegram")
            .finally(() => {
                this.loading = false;
            });
            
        },
        save(configs: CourseConfig) {
            this.loading = true;
            useCoursesStore().editCourseServerInstance(this.course.id, (this.type === "discord_user" || this.type === "discord_other") ? "discord" : "telegram", {
               ...this.instance,
               config: configs,
               creator_id: useUserStore().user?.id as number,
            })
            .finally(() => {
                this.loading = false;
            });
        },
    },
});
</script>

<style lang="scss">
.instance-item {
    position: relative;
    width: 100%;

    .q-item {
        align-items: center;
        border-bottom: 1px solid $color-bk;
    }

    .configs {
        background-color: $color-c;
    }
}
</style>
