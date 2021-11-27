## My first big project
### This a handwritten Snake game.

To crate jar:
> jar cvf asd.jar .

About configurations:
>CELL_SIZE = 40 // size of one cell on the field
CANVAS_WIDTH = 20 // field's width (in cells)
CANVAS_HEIGHT = 15 // field's width (in cells)
KEY_LEFT = 37 // ← key bind
KEY_UP = 38 // ↑ key bind
KEY_RIGHT = 39 // → key bind
KEY_DOWN = 40 // ↓ key bind
START_SNAKE_SIZE = 3 // Snake size after on start of the game
ADD_AFTER_EAT = 1 // TODO: adaptive chaning size on eating
SNAKE_DELAY = 100 // Delay between frames (100 < delay < 150 more comfortable)
GAME_OVER_DELAY = 2000 // Delay on GAME OVER screen
INF_FIELD = true // if true - field is a loop : else if you touch a boards - GAME OVER
