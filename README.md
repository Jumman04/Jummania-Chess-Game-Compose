<p align="center">
   <img src="https://github.com/user-attachments/assets/b17218f0-d731-4c77-afb0-90ae43cec929" alt="App Icon" width="666">
   <br>
   <a href="https://github.com/Jumman04/Jummania-Chess-Game-Compose/network/members">
   <img src="https://img.shields.io/github/forks/Jumman04/Jummania-Chess-Game-Compose" alt="GitHub Forks"/>
   </a>
   <a href="https://github.com/Jumman04/Jummania-Chess-Game-Compose/stargazers">
   <img src="https://img.shields.io/github/stars/Jumman04/Jummania-Chess-Game-Compose" alt="GitHub Stars"/>
   </a>
<img src="https://img.shields.io/github/license/Jumman04/Jummania-Chess-Game-Compose.svg?cacheBust=1" />
</p>

# ♟️ Jummania-Chess-Game-Compose<br>— Customizable Chess Game for Compose Multiplatform

**Jummania-Chess-Game-Compose** is a fully-functional, customizable chess game built with **Jetpack
Compose Multiplatform**.  
It offers smooth interactions, realistic piece movements, and a highly customizable interface —
including board themes, piece styles, stroke options, and more.

This project includes complete chess gameplay logic:  
legal move validation, turn-based play, pawn promotion, and piece movement rules.  
It can be extended with AI opponents or multiplayer features for a seamless experience.

Designed for **Jetpack Compose UI**, it works across **Android**, **Desktop**, **Web**, and **other
platforms** with the same smooth gameplay experience.

You can explore the original version of this game in the
repository: [Jummania Chess Game on GitHub](https://github.com/Jumman04/Jummania-Chess-Game).

This project has since been extended to support Jetpack Compose, enabling a modern and
cross-platform chess game experience.

---

## ✨ Features

- 🎨 **Customizable Board Colors**: Define the colors of light and dark squares.
- ♟️ **Customizable Piece Styles**:
    - Choose between filled or outlined symbols
    - Select from different piece font styles (*Standard*, *Classic*, *Merida*, *Symbola*)
    - Option for bold symbols
- 🖌️ **Custom Stroke Effects**: Customize stroke widths and colors for pieces and squares.
- 🕹️ **Smooth Piece Movements**: Supports dragging and dropping of chess pieces.
- ✅ **Complete Chess Rules**: Validates legal moves and ensures correct piece movements.
- 🔄 **Turn Tracking**: Displays whose turn it is with real-time updates.
- 📜 **Pawn Promotion**: Automatically handles pawn promotion.
- ✨ **Highlight Valid Moves**: Visual cues show valid squares for each piece.
- 📱 **Responsive Chessboard**: Adapts to different screen sizes.
- 🛠️ **Composable API**: Easy to integrate with any Jetpack Compose UI.
- 🚀 **Multiplatform Support**: Works on Android, Desktop, Web, and other platforms via Compose
  Multiplatform.
- ⚡ **Lightweight & Standalone**: No heavy dependencies, just pure Compose code.
- 📚 **Fully Documented Code**: Easy to extend, modify, and understand.

---

## 🧩 How to Use

Using the game is simple — just call the `ChessBoardCanvas` composable in your screen layout:

```kotlin
ChessBoardCanvas(
    chessController = chessController,  // Optional: your custom controller to manage the game state
    squareColors = SquareColors(),     // Optional: customize square colors
    symbolStyle = SymbolStyle(),       // Optional: customize piece symbols
    stroke = Stroke()                  // Optional: customize stroke effects
)
```

**Example**:

```kotlin
@Composable
fun ChessGameScreen() {
    ChessBoardCanvas()  // Uses default settings for a simple game
}
```

The game board is now interactive, and you can customize the colors, pieces, and strokes as needed.

---

## 🛠️ Technologies Used

- **Kotlin**: The primary language used for building the game, leveraging Kotlin's conciseness and
  power for Android development.
- **Jetpack Compose**: A modern toolkit for building native UIs in Android, enabling declarative UI
  components and simplifying layout creation with Compose-based architecture.
- **Android Studio**: The official Integrated Development Environment (IDE) for building, testing,
  and deploying the game on Android devices, offering powerful debugging and profiling tools.
- **Compose Multiplatform**: Utilized for creating a unified experience across multiple platforms
  like Android, Web, and Desktop. It enables a single codebase for the chess game to work seamlessly
  across different environments.
- **Custom Views**: A custom `ChessBoardCanvas` composable was developed to handle the game board
  rendering, piece movement, and interactions, giving developers full control over the UI.
- **Material Design**: Applied Material Design principles to ensure the game follows modern UI
  guidelines, offering an intuitive and consistent experience across devices and screen sizes.
- **State Management**: Managed game state with Jetpack Compose’s state and side-effect handling to
  ensure the UI is always in sync with the game logic and player's actions.
- **Multiplayer Structure**: Built with a scalable multiplayer-ready framework to add online play
  capabilities in the future.
- **Original Library**: [Jummania-Chess-Game](https://github.com/Jumman04/Jummania-Chess-Game) - The
  original **Jummania Chess Game** library, developed with custom views for Android, allows
  developers to integrate chess functionality into Android apps. This project is the base from which
  the Compose-based version evolved, providing a seamless and fully-featured chess experience.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
