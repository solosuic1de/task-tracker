--insert into users table
--The value of all passwords is 12345678
--encrypted by https://bcrypt-generator.com/, (12 rounds)
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('ivanov@example.com', 'Ivan', 'Ivanov',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('petrenko@example.com', 'Petrenko', 'Petro',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('alexandrov@email.com', 'Alexandr', 'Alexandrov',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('dmytriev@email.com', 'Dmytro', 'Dmytriev',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('myhailov@email.com', 'Myhailo', 'Myhailov',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('sergeevna@email.com', 'Maria', 'Serveevna',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('pavlovna@email.com', 'Nina', 'Pavlovna',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('vladimirovna@email.com', 'Irina', 'Vladimirovna',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('mykytaev@email.com', 'Mykyto', 'Mykytaev',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('andreev@email.com', 'Andrey', 'Adnreev',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');
INSERT INTO public.users(email, first_name, last_name, password)
VALUES ('evgenyev@email.com', 'Evgen', 'Engenyev',
        '$2y$12$VrZvVZNaGUa4M6oXPNEFOuI6dO97Txlka/qyK8/ciK7MQCjiM3mj6');


--insert into task table
INSERT INTO public.tasks(description, title, user_id, status)
VALUES ('testTask', 'Ivanov`s viewed task', 1, 'View');
INSERT INTO public.tasks(description, title, user_id, status)
VALUES ('testTask', 'Ivanov`s in progress task', 1, 'In_Progress');
INSERT INTO public.tasks(description, title, user_id, status)
VALUES ('testTask', 'Ivanov`s finished task', 1, 'Done');
INSERT INTO public.tasks(description, title, user_id, status)
VALUES ('testTask', 'Petrenko`s task', 2, 'In_Progress');
INSERT INTO public.tasks(description, title, user_id, status)
VALUES ('testTask', 'Alexandrov`s task', 3, 'Done');