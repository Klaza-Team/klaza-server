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
            <course-configs :course="localCourse" type="user" />
        </q-scroll-area>
    </modal-template>

</template>

<script lang="ts">
import { CourseDTO } from "src/@types/dtos";
import { defineComponent, PropType } from "vue";

import ModalTemplate from "src/components/geral/ModalTemplate.vue";
import CourseConfigs from "src/components/geral/CourseConfigs.vue";

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
            type: Object as PropType<CourseDTO>,
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
            set(value: CourseDTO) {
                //TODO - da update no store
                this.$emit("update:course", value);
            },
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
