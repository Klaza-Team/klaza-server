<template>
    <q-page class="row items-center justify-evenly">
        <div class="row items-center justify-evenly fit">
            <h2 class="text-bk">Configurações de servidores</h2>
        </div>

        <div class="column">
            <div class="fit row wrap justify-center items-center q-gutter-lg">
                <course-card
                    v-for="c in courses"
                    type="server"
                    :key="c.id"
                    :course="c"
                    :selected="selected"
                    :mulitple="multiple"
                    @add-selected="addSelected"
                    @remove-selected="removeSelected"
                    @set-edit="setEdit"
                ></course-card>
            </div>
        </div>

        <modal-edit v-model="showEdit" :course="editCourse" />
    </q-page>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useCoursesStore } from "stores/courses";

import CourseCard from "src/components/CourseCard.vue";
import ModalEdit from "components/ModalEdit.vue";
import { CourseDTO } from "src/@types/dtos";

export default defineComponent({
    name: "IndexPage",
    components: {
        CourseCard,
        ModalEdit,
    },
    data() {
        return {
            multiple: false,
            selected: [] as number[],

            showEdit: false,
            editCourse: {} as CourseDTO,
        };
    },
    computed: {
        courses() {
            return useCoursesStore().sortServerCoursesActivedAndFullName;
        },
    },
    methods: {
        activeAll() {
            //TODO - adicionar logica para ativar todos os cursos
        },
        disableAll() {
            //TODO - adicionar logica para desativar todos os cursos
        },
        addSelected(id: number) {
            this.selected.push(id);
        },
        removeSelected(id: number) {
            this.selected.splice(this.selected.indexOf(id), 1);
        },
        setEdit(course: CourseDTO) {
            this.editCourse = course;
            this.showEdit = true;
        },
    },
});
</script>
