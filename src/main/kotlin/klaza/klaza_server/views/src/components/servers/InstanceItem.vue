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
                />
            </q-card-section>
        </q-card>
    </q-expansion-item>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import {
    CourseDTO,
    UserCourseDiscordConfigDTO,
    UserCourseTelegramConfigDTO,
} from "src/@types/dtos";
import CourseConfigs from "src/components/geral/CourseConfigs.vue";

export default defineComponent({
    name: "CourseItem",
    components: {
        CourseConfigs,
    },
    props: {
        course: {
            type: Object as () => CourseDTO,
            required: true,
        },
        instance: {
            type: Object as () =>
                | UserCourseDiscordConfigDTO
                | UserCourseTelegramConfigDTO,
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
    methods: {
        remove() {
            //TODO - remover do banco
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
