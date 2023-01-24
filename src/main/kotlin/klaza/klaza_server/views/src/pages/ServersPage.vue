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

        <modal-view-server v-model="showView" :course="viewCourse" />
    </q-page>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useCoursesStore } from "stores/courses";
import { useUserStore } from "stores/user";

import CourseCard from "src/components/geral/CourseCard.vue";
import ModalViewServer from "src/components/servers/ModalViewServer.vue";
import { Course } from "src/@types/models.js";

import { RouteLocationNormalizedLoaded } from "vue-router"

export default defineComponent({
    name: "ServersPage",
    components: {
        CourseCard,
        ModalViewServer,
    },
    data() {
        return {
            multiple: false,
            selected: [] as number[],

            showView: false,
            viewCourse: {} as Course,
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
        setEdit(course: Course) {
            this.$router.replace({ query: { modal_view: course.id } })
            this.$route
        },
        showModalView() {
            const query = this.$route.query;
            if (query.modal_view) {

                const courseQuery = useCoursesStore().getCourseById(parseInt(query.modal_view as string));

                if (courseQuery) {
                    this.viewCourse = courseQuery
                    this.showView = true;
                }
                else {

                    const newRouterQuery = { ...this.$route.query };
                    delete newRouterQuery.modal_view;

                    this.$router.replace({ query: newRouterQuery })
                }
            }
        },
    },
    async mounted() { 
        await useUserStore().getUser();
	    await useCoursesStore().getCourses();
        this.showModalView() 
    },
    watch: {
        $route(to: RouteLocationNormalizedLoaded, from: RouteLocationNormalizedLoaded){
            if (to.query.modal_view && to.query.modal_view !== from.query.modal_view) {
                this.showModalView();
            }
        }
    },
});
</script>
