<template>
	<q-layout view="hHh Lpr lFf">

		<q-header class="bg-a">
			<q-toolbar>
				<q-btn
					flat
					dense
					round
					icon="fa-solid fa-bars"
					aria-label="Menu"
					@click="toggleLeftDrawer"
				/>

				<q-space />

				<q-avatar>
					<!-- //TODO - colocar a imagem do usuário -->
					<img src="https://cdn.quasar.dev/img/avatar.png" />

					<q-tooltip class="tooltip" anchor="top middle" self="bottom middle">
						Perfil
					</q-tooltip>

				</q-avatar>

			</q-toolbar>

		</q-header>

		<q-drawer
			v-model="leftDrawerOpen"
			class="bg-a"
		>
			<q-list>
				


			</q-list>

		</q-drawer>

		<q-page-container>
			<router-view />
		</q-page-container>

	</q-layout>

</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
	name: 'MainLayout',

	setup () {
		const leftDrawerOpen = ref(false)

		return {
			leftDrawerOpen,
			toggleLeftDrawer () {
				leftDrawerOpen.value = !leftDrawerOpen.value
			}
		}
	},
});
</script>
