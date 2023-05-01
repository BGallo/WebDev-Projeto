
CREATE TABLE IF NOT EXISTS public.hotel
(
    id_ integer NOT NULL DEFAULT nextval('hotel_id__seq'::regclass),
    nome text COLLATE pg_catalog."default",
    endereco text COLLATE pg_catalog."default",
    cep text COLLATE pg_catalog."default",
    estrelas integer,
    diaria double precision,
    CONSTRAINT hotel_pkey PRIMARY KEY (id_)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.hotel
    OWNER to postgres;
