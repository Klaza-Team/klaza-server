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
					color="w"
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
			overlay
		>
			<q-list class="text-w">
				
				<q-item to="/klaza/dashboard" active-class="text-c" clickable v-ripple>
					<q-item-section avatar>
						<q-icon name="fa-solid fa-user-gear" />
					</q-item-section>
					<q-item-section>
						<q-item-label>Configurações diretas</q-item-label>
					</q-item-section>
				</q-item>

				<q-item to="/klaza/servers" active-class="text-c" clickable v-ripple>
					<q-item-section avatar>
						<q-icon name="fa-solid fa-server" />
					</q-item-section>
					<q-item-section>
						<q-item-label>Configurações servers</q-item-label>
					</q-item-section>
				</q-item>

				<q-item to="/klaza/perfil" active-class="text-c" clickable v-ripple>
					<q-item-section avatar>
						<q-icon name="fa-solid fa-user" />
					</q-item-section>
					<q-item-section>
						<q-item-label>Perfil</q-item-label>
					</q-item-section>
				</q-item>

			</q-list>

		</q-drawer>

		<q-page-container>
			<router-view class="bg-c" />
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
