
typealias  SudokuBoard = MutableList<CharArray>

val MODE_ROW = 2
val MODE_COL = 1

fun SudokuBoard.validBlock(i: Int, j: Int, mode: Int, blockSize: Int): Boolean {
    for (ii in 0..blockSize) {
        val set: MutableSet<Char> = HashSet()
        for (jj in 0..blockSize) {
            var row: Int = -1
            var col: Int = -1
            if (mode == MODE_ROW) {
                row = ii + i * blockSize
                col = jj + j * blockSize
            } else if (mode == MODE_COL) {
                row = jj + j * blockSize
                col = ii + i * blockSize
            }

            val elem = this[row][col]
            if (elem == '.')
                continue

            if (set.contains(elem))
                return false

            set.add(elem)
        }
    }

    return true
}

fun sol(board: MutableList<CharArray>): Boolean {
    for (i in 0..3) {
        for (j in 0..3) {
            if (board.validBlock(i, j, MODE_ROW, 3 ) &&
                    board.validBlock(i,j,MODE_COL, 3))
                continue
            else
                return false
        }
    }
    return board.validBlock(0,0, MODE_ROW, 9) && board.validBlock(0,0,MODE_COL,9)
}