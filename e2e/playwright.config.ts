import { defineConfig } from '@playwright/test';

// 事前に DB・API・画面を起動しておく（README 参照）
export default defineConfig({
  testDir: 'tests',
  timeout: 30_000,
  retries: process.env.CI ? 1 : 0,
  use: { baseURL: process.env.BASE_URL ?? 'http://localhost:5173' },
  reporter: [['list'], ['html', { open: 'never' }]],
});
