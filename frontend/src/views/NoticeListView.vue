<script setup lang="ts">
// @trace DD-notice-001
import { onMounted } from 'vue';
import { useNoticeStore } from '../stores/notice';

const store = useNoticeStore();

const formatDate = (iso: string): string =>
  new Date(iso).toLocaleString('ja-JP', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
  });

onMounted(() => store.fetch(0));
</script>

<template>
  <section>
    <h1>お知らせ</h1>
    <p v-if="store.status === 'loading'">読み込み中…</p>
    <p v-else-if="store.status === 'error'" data-testid="notice-error">お知らせを取得できませんでした</p>
    <p v-else-if="store.items.length === 0" data-testid="notice-empty">お知らせはありません</p>
    <ul v-else data-testid="notice-list">
      <li v-for="n in store.items" :key="n.id" data-testid="notice-item">
        <time :datetime="n.publishedAt">{{ formatDate(n.publishedAt) }}</time>
        {{ n.title }}
      </li>
    </ul>
  </section>
</template>
