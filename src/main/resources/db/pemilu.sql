CREATE TABLE if not exists public.dapil (
	id uuid NOT NULL,
	nama_dapil varchar NOT NULL,
	provinsi varchar NOT NULL,
	wilayah_dapil_list text NOT NULL,
	jumlah_kursi int4 NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	deleted_at timestamp NULL,
	CONSTRAINT dapil_pk PRIMARY KEY (id),
	CONSTRAINT dapil_unique UNIQUE (id)
);

CREATE TABLE if not exists public.partai (
	id uuid NOT NULL,
	nama_partai varchar NOT NULL,
	nomor_urut integer NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	deleted_at timestamp NULL,
	CONSTRAINT partai_pk PRIMARY KEY (id),
	CONSTRAINT partai_unique UNIQUE (id)
);

CREATE TABLE if not exists public.caleg (
	id uuid NOT NULL,
	dapil_id uuid NOT NULL,
	partai_id uuid NOT NULL,
	nama varchar NOT NULL,
	nomor_urut integer NULL,
	jenis_kelamin varchar NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	deleted_at timestamp NULL,
	CONSTRAINT caleg_pk PRIMARY KEY (id),
	CONSTRAINT caleg_unique UNIQUE (id),
	CONSTRAINT caleg_dapil_fk FOREIGN KEY (dapil_id) REFERENCES public.dapil(id),
	CONSTRAINT caleg_partai_fk FOREIGN KEY (partai_id) REFERENCES public.partai(id)
);