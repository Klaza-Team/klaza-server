import { defineStore } from 'pinia';
import { CourseDTO } from "src/@types/dtos";

// TODO: Pegar infos do server
export const useCoursesStore = defineStore('courses', {

    state: () => ({
		courses: [
			{
				id: 3,
				fullName: "Introdução a inteligência artificial",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: true
			},
			{ 
				id: 6,
				fullName: "Curso Técnico em Informática e Curso Técnico em desenvolvimento de sistemas",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: false
			},
			{
				id: 1,
				fullName: "Seminario para TCC",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: true
			},
			{
				id: 4,
				fullName: "Engenharia de Software",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: false
			},
			{
				id: 2,
				fullName: "Empreendedorismo",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: true
			},
			{ 
				id: 5,
				fullName: "Introdução a burrice artificial",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: false
			},
			{ 
				id: 8,
				fullName: "Introdução a Introdução de inteligência artificial",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: false
			},
			{ 
				id: 7,
				fullName: "Projeto de Sistemas",
				shortName: "",
				image: "https://i.imgur.com/4Z5j5Zm.png",
				actived: false
			},

		] as CourseDTO[],
    }),

    getters: {
		getCourseById: (state) => {
			return (id: number) => state.courses.find(course => course.id === id);
		},
		sortCoursesById: (state) => {
			return state.courses.sort((a, b) => a.id - b.id);
		},
		sortCoursesByFullName: (state) => {
			return state.courses.sort((a, b) => a.fullName.localeCompare(b.fullName));
		},
		sortCoursesByActived: (state) => {
			return state.courses.sort((a) => a.actived ? -1 : 1);
		},
		sortCoursesActivedAndFullName: (state) => {
			return state.courses.sort((a, b) => {

				if (a.actived && !b.actived) {
					return -1;
				}

				if (!a.actived && b.actived) {
					return 1;
				}

				return a.fullName.localeCompare(b.fullName);

			});
		}
	},

    actions: {}
});
