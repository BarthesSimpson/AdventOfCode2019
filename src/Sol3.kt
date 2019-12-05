import java.io.File

enum class Orientation {
    HORIZONTAL, VERTICAL
}

data class Point(val x: Int, val y: Int)
data class WireSegment(val start: Point, val end: Point, val orientation: Orientation)

class Sol3 {
    fun processFile(filepath: String): Int {

        val wires = File(filepath).useLines {
            it.toList().map { n -> generateWire(n) }
        }

        return distanceOf(closestIntersection(wires))
    }

    fun generateWire(line: String): List<WireSegment> {
        val moves = line.split(",")
        var segments = mutableListOf<WireSegment>()
        var curr = Point(0, 0)
        var prev = Point(0, 0)
        for (m in moves) {
            val dir = m[0]
            val dist = m.substring(1).toInt()
            curr = when (dir) {
                'U' -> Point(curr.x, curr.y + dist)
                'D' -> Point(curr.x, curr.y - dist)
                'L' -> Point(curr.x - dist, curr.y)
                'R' -> Point(curr.x + dist, curr.y)
                else -> throw Exception("Instruction $m had unexpected direction $dir")
            }
            val seg = when (dir) {
                'U' -> WireSegment(prev, curr, Orientation.VERTICAL)
                'D' -> WireSegment(curr, prev, Orientation.VERTICAL)
                'L' -> WireSegment(curr, prev, Orientation.HORIZONTAL)
                'R' -> WireSegment(prev, curr, Orientation.HORIZONTAL)
                else -> throw Exception("Instruction $m had unexpected direction $dir")
            }
            segments.add(seg)
            prev = curr
        }
        return segments
    }

    private fun closestIntersection(wires: List<List<WireSegment>>): Point {
        return getIntersections(wires).sortedBy { distanceOf(it) }[0]
    }

    // Can use Bentley-Ottmann for O(n log n) time complexity, but this is AoC so O(n**2) is fine
    private fun getIntersections(wires: List<List<WireSegment>>): List<Point> {
        val intersections = mutableListOf<Point>()
        for (seg1 in wires[0]) {
            for (seg2 in wires[1]) {
                if (seg1.orientation == seg2.orientation) {
                    continue
                }
                val intersection = when (seg1.orientation) {
                    Orientation.HORIZONTAL -> getIntersection(seg1, seg2)
                    else -> getIntersection(seg2, seg1)
                }
                if (intersection != null) {
                    intersections.add(intersection)
                }
            }
        }
        return intersections
    }

    private fun getIntersection(horizontal: WireSegment, vertical: WireSegment): Point? {
        if (vertical.start.y < horizontal.start.y && vertical.end.y > horizontal.start.y &&
            vertical.start.x > horizontal.start.x && vertical.end.x < horizontal.end.x
        ) {
            return Point(vertical.start.x, horizontal.start.y)
        }
        return null
    }

    private fun distanceOf(point: Point): Int {
        return kotlin.math.abs(point.x) + kotlin.math.abs(point.y)
    }
}

fun main() {
    val inputFile = "input/3.txt"
    println(Sol3().processFile(inputFile))
}
