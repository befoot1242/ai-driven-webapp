// @trace TS-001
import { createPinia, setActivePinia } from 'pinia';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import * as api from '../api/notices';
import { useNoticeStore } from './notice';

describe('TS-001 お知らせストア', () => {
  beforeEach(() => {
    setActivePinia(createPinia());
    vi.restoreAllMocks();
  });

  it('No.4 正常: 取得すると loaded になり一覧が入る', async () => {
    vi.spyOn(api, 'listNotices').mockResolvedValue({
      items: [
        { id: 2, title: 'B', publishedAt: '2026-11-21T00:00:00+09:00' },
        { id: 1, title: 'A', publishedAt: '2026-11-20T00:00:00+09:00' },
      ],
      page: 0,
      size: 20,
      total: 2,
    });
    const store = useNoticeStore();

    await store.fetch(0);

    expect(store.status).toBe('loaded');
    expect(store.items.map((n) => n.id)).toEqual([2, 1]);
    expect(store.total).toBe(2);
  });

  it('No.5 異常: API が失敗すると error になる', async () => {
    vi.spyOn(api, 'listNotices').mockRejectedValue(new Error('500'));
    vi.spyOn(console, 'error').mockImplementation(() => {});
    const store = useNoticeStore();

    await store.fetch(0);

    expect(store.status).toBe('error');
  });
});
