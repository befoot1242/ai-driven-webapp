// @trace DD-notice-001
// 状態の更新は action（fetch）経由のみ。コンポーネントから直接書き換えない。

import { defineStore } from 'pinia';
import { ref } from 'vue';
import { listNotices, type Notice } from '../api/notices';

export type LoadStatus = 'idle' | 'loading' | 'loaded' | 'error';

export const useNoticeStore = defineStore('notice', () => {
  const items = ref<Notice[]>([]);
  const total = ref(0);
  const page = ref(0);
  const status = ref<LoadStatus>('idle');

  async function fetch(nextPage = 0): Promise<void> {
    status.value = 'loading';
    try {
      const res = await listNotices(nextPage);
      items.value = res.items;
      total.value = res.total;
      page.value = res.page;
      status.value = 'loaded';
    } catch (e) {
      console.error(e);
      status.value = 'error';
    }
  }

  return { items, total, page, status, fetch };
});
