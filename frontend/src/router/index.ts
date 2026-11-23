import { createRouter, createWebHistory } from 'vue-router';
import NoticeListView from '../views/NoticeListView.vue';

export const router = createRouter({
  history: createWebHistory(),
  routes: [{ path: '/', name: 'notice-list', component: NoticeListView }],
});
