import { RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [

	{ path: "/", redirect: { name: "Dashboard" } },

	{
		path: "/klaza/login",
		name: "Login",
		component: () => import("layouts/LoginLayout.vue"),
		children: [{ path: "", component: () => import("pages/LoginPage.vue") }],
	},


	{
		path: "/klaza/dashboard",
		name: "Dashboard",
		component: () => import("layouts/MainLayout.vue"),
		children: [{ path: "", component: () => import("pages/IndexPage.vue") }],
	},

	// Always leave this as last one,
	// but you can also remove it
	{
		path: "/:catchAll(.*)*",
		name: "NotFound",
		component: () => import("pages/ErrorNotFound.vue"),
	},
];

export default routes;
