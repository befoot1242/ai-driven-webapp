// @trace DD-notice-001
// api/openapi.yaml の listNotices に対応する。型は OpenAPI の schema に合わせる。

export interface Notice {
  id: number;
  title: string;
  publishedAt: string; // ISO 8601
}

export interface NoticePage {
  items: Notice[];
  page: number;
  size: number;
  total: number;
}

export async function listNotices(page = 0, size = 20): Promise<NoticePage> {
  const res = await fetch(`/api/notices?page=${page}&size=${size}`);
  if (!res.ok) {
    throw new Error(`listNotices failed: ${res.status}`);
  }
  return (await res.json()) as NoticePage;
}
