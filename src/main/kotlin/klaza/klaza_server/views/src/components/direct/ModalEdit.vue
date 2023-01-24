<template>

    <modal-template v-model="show" :title="`Editar configurações do curso ${localCourse.fullName}`">
        <div class="text-h6 text-w text-center">
                    {{ localCourse.fullName }}
        </div>
        <div class="full-width column content-center">
            <div class="text-w row justify-between items-center" style="width: 370px;">
                <span>Ativar notificações para esse curso</span>
                <q-toggle
                    v-model="localCourse.actived"
                    color="bk"
                    checked-icon="fa-solid fa-check"
                    unchecked-icon="fa-solid fa-times"
                />
            </div>
        </div>
        <q-scroll-area>
            <course-configs :course="localCourse" type="user" :loading="loading"  @save="save"  />
        </q-scroll-area>
    </modal-template>

</template>

<script lang="ts">
import { Course, CourseConfig } from "src/@types/models.js";
import { defineComponent, PropType } from "vue";

import ModalTemplate from "src/components/geral/ModalTemplate.vue";
import CourseConfigs from "src/components/geral/CourseConfigs.vue";
import { useCoursesStore } from "src/stores/courses";
import { useUserStore } from "src/stores/user";

export default defineComponent({
    name: "ModalEdit",
    components: {
        ModalTemplate,
        CourseConfigs
    },
    props: {
        value: {
            type: Boolean,
            default: false,
        },
        course: {
            type: Object as PropType<Course>,
            default: () => ({}),
            required: true,
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
        localCourse: {
            get() {
                return this.course;
            },
            set(value: Course) {
                this.$emit("update:course", value);
            },
        },
    },
    data() {
        return {
            loading: false,
        };
    },
    methods: {
        save(config: CourseConfig) {
            useCoursesStore().updateCourseUserConfig(this.localCourse.id, useUserStore().user?.id as number, config).finally(() => {
                this.loading = false;
            });
        },
    }
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

.q-scrollarea {
    width: 100%; 
    height: 300px;
}

</style>

<style lang="scss">

.q-scrollarea {
    .q-scrollarea__content {
        display: flex;
        justify-content: space-around;
        align-items: center;
    }
    .q-scrollarea__content > div {
        width: 95%;
    }
}


</style>
