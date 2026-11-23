-- @trace DD-notice-001
CREATE TABLE notices (
    id           bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title        varchar(200) NOT NULL,
    body         text         NOT NULL DEFAULT '',
    published_at timestamptz  NOT NULL,
    created_at   timestamptz  NOT NULL DEFAULT now(),
    updated_at   timestamptz  NOT NULL DEFAULT now()
);

CREATE INDEX idx_notices_published_at ON notices (published_at DESC);

COMMENT ON TABLE  notices              IS 'お知らせ';
COMMENT ON COLUMN notices.title        IS 'タイトル';
COMMENT ON COLUMN notices.body         IS '本文';
COMMENT ON COLUMN notices.published_at IS '公開日時（この日時以降に表示）';
