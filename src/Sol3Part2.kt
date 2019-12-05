import java.io.File

data class CumulativeWireSegment(val segment: WireSegment, val steps: Int, val dir: Char)
data class CumulativePoint(val point: Point, val steps: Int)

class Sol3Part2 {
    fun processFile(filepath: String): Int {

        val wires = File(filepath).useLines {
            it.toList().map { n -> generateWire(n) }
        }

        return closestIntersection(wires).steps
    }

    fun generateWire(line: String): List<CumulativeWireSegment> {
        val moves = line.split(",")
        var segments = mutableListOf<CumulativeWireSegment>()
        var curr = Point(0, 0)
        var prev = Point(0, 0)
        var steps = 0
        for (m in moves) {
            val dir = m[0]
            val dist = m.substring(1).toInt()
            steps += dist
            curr = when (dir) {
                'U' -> Point(curr.x, curr.y + dist)
                'D' -> Point(curr.x, curr.y - dist)
                'L' -> Point(curr.x - dist, curr.y)
                'R' -> Point(curr.x + dist, curr.y)
                else -> throw Exception("Instruction $m had unexpected direction $dir")
            }
            val seg = when (dir) {
                'U' -> CumulativeWireSegment(WireSegment(prev, curr, Orientation.VERTICAL), steps, dir)
                'D' -> CumulativeWireSegment(WireSegment(curr, prev, Orientation.VERTICAL), steps, dir)
                'L' -> CumulativeWireSegment(WireSegment(curr, prev, Orientation.HORIZONTAL), steps, dir)
                'R' -> CumulativeWireSegment(WireSegment(prev, curr, Orientation.HORIZONTAL), steps, dir)
                else -> throw Exception("Instruction $m had unexpected direction $dir")
            }
            segments.add(seg)
            prev = curr
        }
        return segments
    }

    private fun closestIntersection(wires: List<List<CumulativeWireSegment>>): CumulativePoint {
        return getIntersections(wires).sortedBy { it.steps }[0]
    }

    // Can use Bentley-Ottmann for O(n log n) time complexity, but this is AoC so O(n**2) is fine
    private fun getIntersections(wires: List<List<CumulativeWireSegment>>): List<CumulativePoint> {
        val intersections = mutableListOf<CumulativePoint>()
        for (seg1 in wires[0]) {
            for (seg2 in wires[1]) {
                if (seg1.segment.orientation == seg2.segment.orientation) {
                    continue
                }
                val intersection = when (seg1.segment.orientation) {
                    Orientation.HORIZONTAL -> getIntersection(seg1, seg2)
                    else -> getIntersection(seg2, seg1)
                }
                if (intersection != null) {
                    intersections.add(intersection)
                }
            }
        }
        println(intersections)
        return intersections
    }

    private fun getIntersection(horizontal: CumulativeWireSegment, vertical: CumulativeWireSegment): CumulativePoint? {
        if (vertical.segment.start.y < horizontal.segment.start.y && vertical.segment.end.y > horizontal.segment.start.y &&
            vertical.segment.start.x > horizontal.segment.start.x && vertical.segment.end.x < horizontal.segment.end.x
        ) {
            val stepsCeil = horizontal.steps + vertical.steps
            val horizontalAdj =
                if (horizontal.dir == 'R') {
                    horizontal.segment.end.x - vertical.segment.end.x
                } else {
                    vertical.segment.end.x - horizontal.segment.start.x
                }
            val verticalAdj =
                if (vertical.dir == 'U') {
                    vertical.segment.end.y - horizontal.segment.end.y
                } else {
                    horizontal.segment.end.y - vertical.segment.start.y
                }

            return CumulativePoint(
                Point(vertical.segment.start.x, horizontal.segment.start.y),
                stepsCeil - horizontalAdj - verticalAdj
            )
        }
        return null
    }
}

fun main() {
    val inputFile = "input/3.txt"
    println(Sol3Part2().processFile(inputFile))
}
