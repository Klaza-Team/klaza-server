<template>
    <q-page class="row items-center justify-evenly">
      
		<div class="row items-center justify-evenly fit">
			<h2 class="text-bk">Configurações diretas</h2>
		</div>

		<div class="column">
			
			<div class="fit row justify-end content-center q-pr-xl">

				<div class="row content-center">
					<q-icon name="fa-solid fa-circle-question cursor-pointer" size="sm">
						<q-tooltip class="tooltip text-center">
							<span>Caso queira ativar/desativar varias configurações <br> clique em </span>“<span class="text-bk">selecionar multiplos</span>”
						</q-tooltip>
					</q-icon>
				</div>
			
				<q-checkbox v-model="multiple" color="bk" label="selecionar multiplos" />
			
			</div>

			<div v-show="multiple" class="row content-center justify-center q-mb-lg q-gutter-x-xl">
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
				<course-card v-for="c in courses" :key="c.id" :course="c" :selected="selected" @add-selected="addSelected" @remove-selected="removeSelected"></course-card>
			</div>

		</div>

    </q-page>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import CourseCard from "components/CourseCard.vue";
import { useCoursesStore } from "stores/courses"

export default defineComponent({
  	name: 'IndexPage',
	components: {
		CourseCard,
	},
	data() {
		return {
			multiple: true,
			selected: [] as number[],
		}
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
	},
});
</script>
