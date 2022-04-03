# Phonograph
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://github.com/kabouzeid/Phonograph/blob/master/LICENSE.txt)

**A material designed local music player for Android.**

![Screenshots](./art/art.jpg?raw=true)

[<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png"
     alt="Get it on Google Play"
     height="80">](https://play.google.com/store/apps/details?id=com.kabouzeid.gramophone)
[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
     alt="Get it on F-Droid"
     height="80">](https://f-droid.org/packages/com.kabouzeid.gramophone/)

# Текущий прогресс
- Реализован UI-тест "Старт и пауза проигрывания трека"
- Реализован UI-тест "Переключение треков в проигрывателе"
- Реализован UI-тест "Добавление/удаление трека из Избранного"
- Реализован UI-тест "Добавление и удаление трека в плейлисте" (на данный момент функция добавления трека не работает, из-за чего плейлист все равно оказывается пустым)

# Запуск тестов
1. В дереве файлов проекта необходимо раскрыть папку com.kabouzeid.gramophone.ui.activities.
2. Выберите конкретный Kotlin-файл, после чего нажмите ПКМ -> Run 'название_теста'.
3. Чтобы запустить все тесты сразу, нажмите ПКМ по com.kabouzeid.gramophone.ui.activities и выберите пункт "Run 'Tests in 'com.kabouzeid...'"

Перед запуском тестов необходимо вручную открыть приложение и открыть на нем стартовый экран, раздел "Треки". В противном случае возможны ошибки в тестах.