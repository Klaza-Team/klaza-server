<template>
	<q-page class="row items-center justify-evenly bg-c">
		
		<q-form @submit.prevent="onSubmit">

			<q-card class="h-60vh w-60vw r-20 bg-b text-center column wrap content-center q-gutte-y-xl">

				<q-card-section class="col-3 column justify-center">
					<span class="title"> Login </span>
				</q-card-section>

				<q-card-section class="w-80 col-grow column justify-center">
					
						<q-input
							v-model="username"
							label="Username"
							color="a"
							class="input q-mb-lg"
							@keyup.enter="onSubmit"
							autofocus
							autofill
							outlined
							dark
						>
							<q-tooltip class="tooltip" self="center middle">
								Digite seu login
							</q-tooltip>

						</q-input>
						<q-input
							v-model="password"
							label="Password"
							type="password"
							color="a"
							class="input"
							@keyup.enter="onSubmit"
							outlined
							dark
						>
							<q-tooltip class="tooltip" self="center middle">
								Digite sua senha
							</q-tooltip>

						</q-input>
					
				</q-card-section>

				<q-card-section class="col-3">
					<q-btn
						type="submit"
						color="a"
						label="Entrar"
						class="q-px-xl r-10 f-15"
					/>
				</q-card-section>

			</q-card>

		</q-form>

	</q-page>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { useLoginStore } from "stores/login";

export default defineComponent({

	name: 'LoginPage',
	data() {
		return {

			loginStore: useLoginStore(),
			
			username: '',
			password: '',
			
		};
	},
	methods: {
		onSubmit() {
			
			const auth = this.loginStore.authenticate(this.username, this.password)
			
			if (auth) {
				this.$router.push({ name: 'Dashboard' })
			} 
			else {
				this.$q.notify({
					message: 'Usuário ou senha inválidos',
					color: 'negative',
					position: 'top',
					icon: 'warning'
				})
			}
		}
	}
 
});

</script>

<style lang="scss" scoped>

.title {
	font-size: 48px;
	font-weight: bold;
	color: $color-w;
}

</style>
