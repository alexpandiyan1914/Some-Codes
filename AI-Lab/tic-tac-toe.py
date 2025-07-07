import random

# Initialize board
board = [' ' for _ in range(9)]

# Print the board
def print_board():
    for row in [board[i*3:(i+1)*3] for i in range(3)]:
        print('| ' + ' | '.join(row) + ' |')

# Check winner
def check_winner(brd, player):
    win_conditions = [
        [0,1,2], [3,4,5], [6,7,8],  # Rows
        [0,3,6], [1,4,7], [2,5,8],  # Columns
        [0,4,8], [2,4,6]            # Diagonals
    ]
    for condition in win_conditions:
        if all(brd[i] == player for i in condition):
            return True
    return False

# Check draw
def is_draw():
    return ' ' not in board

# Player move
def player_move():
    while True:
        try:
            move = int(input("Enter your move (1-9): ")) - 1
            if 0 <= move <= 8 and board[move] == ' ':
                board[move] = 'X'
                break
            else:
                print("Invalid move. Try again.")
        except:
            print("Enter a number between 1 and 9.")

# Computer move (random strategy)
def computer_move():
    empty_cells = [i for i in range(9) if board[i] == ' ']
    move = random.choice(empty_cells)
    board[move] = 'O'
    print(f"Computer chose position {move + 1}")

# Game loop
def play_game():
    print("Welcome to Tic Tac Toe (You: X, Computer: O)")
    print_board()

    while True:
        player_move()
        print_board()
        if check_winner(board, 'X'):
            print("You win! 🎉")
            break
        if is_draw():
            print("It's a draw! 🤝")
            break

        computer_move()
        print_board()
        if check_winner(board, 'O'):
            print("Computer wins! 😢")
            break
        if is_draw():
            print("It's a draw! 🤝")
            break

play_game()
