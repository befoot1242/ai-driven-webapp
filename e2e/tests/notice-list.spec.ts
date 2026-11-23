// @trace TS-001
import { expect, test } from '@playwright/test';

// テストデータの投入方法はプロジェクトで決める（例: テスト用 API、SQL 投入スクリプト）。
// ここでは「お知らせ一覧が表示される」ことだけを確認するひな形にしている。
test('TS-001 No.7 トップ画面にお知らせ一覧が表示される', async ({ page }) => {
  await page.goto('/');
  await expect(page.getByRole('heading', { name: 'お知らせ' })).toBeVisible();
  await expect(page.getByTestId('notice-list').or(page.getByTestId('notice-empty'))).toBeVisible();
});
