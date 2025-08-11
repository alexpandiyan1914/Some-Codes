import math
import copy

ROWS = 4
COLS = 4
WIN_COUNT = 3
PLAYER = 'O'  # Human
AI = 'X'      # AI

def create_board():
    return [['.' for _ in range(COLS)] for _ in range(ROWS)]

def print_board(board):
    for row in board:
        print(' '.join(row))
    print()

def get_valid_columns(board):
    return [col for col in range(COLS) if board[0][col] == '.']

def drop_piece(board, col, piece):
    new_board = copy.deepcopy(board)
    for row in reversed(range(ROWS)):
        if new_board[row][col] == '.':
            new_board[row][col] = piece
            break
    return new_board

def is_winning_move(board, piece):
    # Check horizontal
    for r in range(ROWS):
        for c in range(COLS - WIN_COUNT + 1):
            if all(board[r][c+i] == piece for i in range(WIN_COUNT)):
                return True
    # Check vertical
    for r in range(ROWS - WIN_COUNT + 1):
        for c in range(COLS):
            if all(board[r+i][c] == piece for i in range(WIN_COUNT)):
                return True
    # Check diagonal \
    for r in range(ROWS - WIN_COUNT + 1):
        for c in range(COLS - WIN_COUNT + 1):
            if all(board[r+i][c+i] == piece for i in range(WIN_COUNT)):
                return True
    # Check diagonal /
    for r in range(WIN_COUNT - 1, ROWS):
        for c in range(COLS - WIN_COUNT + 1):
            if all(board[r-i][c+i] == piece for i in range(WIN_COUNT)):
                return True
    return False

def is_draw(board):
    return all(board[0][col] != '.' for col in range(COLS))

# Minimax with Alpha-Beta
def minimax(board, depth, alpha, beta, maximizing):
    if is_winning_move(board, AI):
        return None, 10
    if is_winning_move(board, PLAYER):
        return None, -10
    if is_draw(board) or depth == 0:
        return None, 0

    valid_columns = get_valid_columns(board)
    best_col = valid_columns[0]

    if maximizing:
        max_eval = -math.inf
        for col in valid_columns:
            new_board = drop_piece(board, col, AI)
            _, eval = minimax(new_board, depth - 1, alpha, beta, False)
            if eval > max_eval:
                max_eval = eval
                best_col = col
            alpha = max(alpha, eval)
            if beta <= alpha:
                break
        return best_col, max_eval
    else:
        min_eval = math.inf
        for col in valid_columns:
            new_board = drop_piece(board, col, PLAYER)
            _, eval = minimax(new_board, depth - 1, alpha, beta, True)
            if eval < min_eval:
                min_eval = eval
                best_col = col
            beta = min(beta, eval)
            if beta <= alpha:
                break
        return best_col, min_eval

# Human vs AI game loop (optional CLI)
def play_game():
    board = create_board()
    turn = 0  # 0 = Human, 1 = AI

    while True:
        print_board(board)
        if is_winning_move(board, PLAYER):
            print("Human Wins!")
            break
        elif is_winning_move(board, AI):
            print("AI Wins!")
            break
        elif is_draw(board):
            print("Game Draw!")
            break

        if turn == 0:
            col = int(input("Your move (0-3): "))
            if col in get_valid_columns(board):
                board = drop_piece(board, col, PLAYER)
                turn = 1
            else:
                print("Invalid move. Try again.")
        else:
            col, _ = minimax(board, 4, -math.inf, math.inf, True)
            board = drop_piece(board, col, AI)
            print(f"AI plays column {col}")
            turn = 0

if __name__ == "__main__":
    play_game()
