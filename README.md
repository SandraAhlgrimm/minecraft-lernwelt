# 🟩 Minecraft Lernwelt

Ein deutsches Lernspiel im Minecraft-Stil für Kinder der 1./2. Klasse – gedacht für das **Fire-Tablet** (läuft aber in jedem modernen Browser). Mit Drachen, Diamanten und einem Jump-'n'-Run.

![Icon](android/res/mipmap-hdpi/ic_launcher.png)

## 🎮 Was ist drin?

Vier Lern-Minispiele plus ein eigener Weltenbau-Modus:

| Spiel | Was man übt |
|-------|-------------|
| 📖 **Lesen** | Wörter vorlesen (mit langer Lesezeit zum Vorlesen) |
| ✏️ **Schreiben** | Buchstaben / Wörter über eine Bildschirmtastatur schreiben |
| ➕ **Rechnen** | Plus- und Minus-Aufgaben |
| 🐉 **Drachensprung** | Jump-'n'-Run mit **5 Leveln** (steigender Schwierigkeit), Doppelsprung, Feuerbällen, Lava & Gegnern |
| 🔨 **Drachenwelt bauen** | Eigene Level bauen (Stein, Lava, Sprungfeder, Blöcke) |

## 🌍 Sprachen

Das Spiel ist mehrsprachig: **Deutsch 🇩🇪, Englisch 🇬🇧 und Französisch 🇫🇷**. Oben im Menü wechselt ein Flaggen-Knopf die Sprache – Texte, Wörter (Lesen/Schreiben) und die Vorlese-Stimme passen sich automatisch an. Die Wahl wird gespeichert.

## 👪 Eltern-Funktionen

- 💎 **Diamanten-Sperre:** Der Drachensprung kostet **5 Diamanten**. Die sammelt das Kind erst beim Lesen, Schreiben oder Rechnen.
- ⏱️ **20-Minuten-Timer:** Danach erscheint ein Sperrbildschirm.
- 🔐 **Passwort:** Zum Weiterspielen ist die Eltern-PIN nötig (Standard **1144**, in `minecraft-lernwelt.html` bei `ELTERN_PIN` änderbar). Ein „Spiel beenden“-Knopf schließt die App auf dem Tablet.
- 🏆 **Bestenliste:** Das Kind trägt nach jedem geschafften Level seinen Namen ein – mit Punkten und Datum.

## ▶️ Spielen

**Im Browser:** einfach `minecraft-lernwelt.html` öffnen. Alles ist in einer einzigen Datei, offline lauffähig.

**Auf dem Fire-Tablet:** die APK aus den [Releases](../../releases) herunterladen und installieren:

1. Am Tablet **Apps aus unbekannten Quellen** erlauben.
2. `MinecraftLernwelt.apk` aufs Tablet kopieren (USB/Cloud) und antippen → **Installieren**.
3. „Minecraft Lernwelt“ startet im Vollbild-Querformat.

Vorteil der App gegenüber dem Browser: **native deutsche Vorlese-Stimme** (Android TextToSpeech) und ein echtes Auto-Schließen nach Ablauf der Zeit.

## 🛠️ APK selbst bauen

Der Android-Teil (`android/`) ist eine schlanke WebView-App, die die HTML-Datei als `assets/index.html` lädt und Brücken für Vorlesen (`AndroidBridge.sprich`) und App-Schließen (`AndroidBridge.beenden`) bereitstellt. Der Build wurde manuell mit den Android-Build-Tools (aapt → javac → d8 → zipalign → apksigner) und JDK 17 erstellt.

## Lizenz

Privates Familienprojekt.
