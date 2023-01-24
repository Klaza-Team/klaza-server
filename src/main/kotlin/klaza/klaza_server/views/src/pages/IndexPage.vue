<template>
    <q-page class="row items-center justify-evenly">
        <div class="row items-center justify-evenly fit">
            <h2 class="text-bk">Configurações diretas</h2>
        </div>

        <div class="column">
            <div class="fit row justify-end content-center q-pr-xl">
                <div class="row content-center">
                    <q-icon
                        name="fa-solid fa-circle-question cursor-pointer"
                        size="sm"
                    >
                        <q-tooltip class="tooltip text-center">
                            <span
                                >Caso queira ativar/desativar varias
                                configurações <br />
                                clique em </span
                            >“<span class="text-bk">selecionar multiplos</span>”
                        </q-tooltip>
                    </q-icon>
                </div>

                <q-checkbox
                    v-model="multiple"
                    color="bk"
                    label="selecionar multiplos"
                />
            </div>

            <div
                v-show="multiple"
                class="row content-center justify-center q-mb-lg q-gutter-x-xl"
            >
                <q-btn
                    class="q-mr-sm"
                    @click="activeAll"
                    color="b"
                    rounded
                    label="Ativar todos"
                />

                <q-btn
                    class="q-mr-sm"
                    @click="disableAll"
                    color="b"
                    rounded
                    label="Desativar todos"
                />
            </div>

            <div class="fit row wrap justify-center items-center q-gutter-lg">
                <course-card
                    v-for="c in courses"
                    type="direct"
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
import { useUserStore } from "stores/user";

import CourseCard from "src/components/geral/CourseCard.vue";
import ModalEdit from "src/components/direct/ModalEdit.vue";
import { Course } from "src/@types/models.js";

export default defineComponent({
    name: "IndexPage",
    async mounted() {
        await useUserStore().getUser();
	    await useCoursesStore().getCourses();
    },
    components: {
        CourseCard,
        ModalEdit,
    },
    data() {
        return {
            multiple: false,
            selected: [] as number[],

            showEdit: false,
            editCourse: {} as Course,
        };
    },
    computed: {
        courses() {
            return useCoursesStore().sortCoursesActivedAndFullName;
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
        setEdit(course: Course) {
            this.editCourse = course;
            this.showEdit = true;
        },
    },
});
</script>
