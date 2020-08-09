package divinerpg.dimensions.arcana.mazegen;

import divinerpg.dimensions.arcana.mazegen.Cell;
import divinerpg.dimensions.arcana.mazegen.UnionFind;
import divinerpg.registry.StructureRegistry;
import divinerpg.structure.arcana.ArcanaStructureHandler;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.ChunkPos;

import java.util.*;

public class ArcanaMazeGenerator {
    public static final int MAZE_SIZE = 64;

    private static class Edge {
        Cell firstAdjacent, secondAdjacent;
        Direction direction;

        enum Direction {
            HORIZONTAL,
            VERTICAL
        }

        Edge(Cell firstAdjacent, Cell secondAdjacent, Direction direction) {
            this.firstAdjacent = firstAdjacent;
            this.secondAdjacent = secondAdjacent;
            this.direction = direction;
        }
    }

    public static Cell[][] generate(int chunkX, int chunkZ, long worldSeed) {
        //Create new random and seed it with provided variables using the vanilla method
        Random random = new Random();
        random.setSeed(worldSeed);
        long k = random.nextLong() / 2L * 2L + 1L;
        long l = random.nextLong() / 2L * 2L + 1L;
        random.setSeed((long) chunkX * k + (long) chunkZ * l ^ worldSeed);

        //Set up stuff
        Cell[][] grid = new Cell[MAZE_SIZE][MAZE_SIZE];
        ArrayList<Edge> edges = new ArrayList<Edge>(); //Was going to use HashSet but I guess there's no efficient way to obtain random elements from them
        UnionFind<Integer> unionFind = new UnionFind<Integer>();

        //Cells
        for(int x = 0; x < MAZE_SIZE; x++) {
            for(int y = 0; y < MAZE_SIZE; y++) {
                Cell cell = new Cell(x, y);
                grid[x][y] = cell;
                unionFind.add(cell.identifier);
            }
        }

        //Horizontal edges
        for(int x = 1; x < MAZE_SIZE; x++) {
            for(int y = 0; y < MAZE_SIZE; y++) {
                edges.add(new Edge(grid[x - 1][y], grid[x][y], Edge.Direction.HORIZONTAL));
            }
        }

        //Vertical edges
        for(int x = 0; x < MAZE_SIZE; x++) {
            for(int y = 1; y < MAZE_SIZE; y++) {
                edges.add(new Edge(grid[x][y - 1], grid[x][y], Edge.Direction.VERTICAL));
            }
        }

        //Shuffle array using seed
        Collections.shuffle(edges, random);

        //Go through all edges
        for(Edge edge: edges) {

            //Get the cells bordering the edge
            Cell firstCell = edge.firstAdjacent;
            Cell secondCell = edge.secondAdjacent;

            //If they are not already part of the same set then delete the edge and join the cells
            if(unionFind.find(firstCell.identifier) != unionFind.find(secondCell.identifier)) {
                if(edge.direction == Edge.Direction.HORIZONTAL) {
                    firstCell.hasSouthEdge = false;
                    secondCell.hasNorthEdge = false;
                }
                else {
                    firstCell.hasEastEdge = false;
                    secondCell.hasWestEdge = false;
                }
                unionFind.union(firstCell.identifier, secondCell.identifier);
            }

            //Otherwise do nothing
        }

        return grid;
    }

    public static Cell obtainMazePiece(int chunkX, int chunkZ, long worldSeed) {
        Cell[][] mazeMap;

        int regionRootX, regionRootZ;
        int mapCoordinateX, mapCoordinateZ;
        regionRootX = roundUpToMultiple(chunkX, ArcanaMazeGenerator.MAZE_SIZE);
        regionRootZ = roundUpToMultiple(chunkZ, ArcanaMazeGenerator.MAZE_SIZE);

        ChunkPos regionRoot = new ChunkPos(regionRootX, regionRootZ);
        Cell[][] storedGrid = MazeMapMemoryStorage.getMapForChunkPos(regionRoot);
        if(storedGrid == null) {
            mazeMap = ArcanaMazeGenerator.generate(regionRootX, regionRootZ, worldSeed);
            MazeMapMemoryStorage.addMap(regionRoot, mazeMap);
        }
        else {
            mazeMap = storedGrid;
        }

        if(chunkX <= 0) {
            mapCoordinateX = Math.abs(chunkX % ArcanaMazeGenerator.MAZE_SIZE);
        }
        else {
            mapCoordinateX = ArcanaMazeGenerator.MAZE_SIZE - (chunkX % ArcanaMazeGenerator.MAZE_SIZE);
            if(mapCoordinateX == ArcanaMazeGenerator.MAZE_SIZE) { //bit messy but it works
                mapCoordinateX = 0;
            }
        }
        if(chunkZ <= 0) {
            mapCoordinateZ = Math.abs(chunkZ % ArcanaMazeGenerator.MAZE_SIZE);
        }
        else {
            mapCoordinateZ = ArcanaMazeGenerator.MAZE_SIZE - (chunkZ % ArcanaMazeGenerator.MAZE_SIZE);
            if(mapCoordinateZ == ArcanaMazeGenerator.MAZE_SIZE) {
                mapCoordinateZ = 0;
            }
        }

        Cell cell = mazeMap[mapCoordinateZ][mapCoordinateX]; //z has to come first because arrays are backwards from Cartesian plane logic
        return cell;
    }

    private static int roundUpToMultiple(int numToRound, int multiple)
    {
        if (multiple == 0) {
            return numToRound;
        }

        int remainder = Math.abs(numToRound) % multiple;
        if (remainder == 0) {
            return numToRound;
        }

        if (numToRound < 0) {
            return -1 * (Math.abs(numToRound) - remainder);
        }
        else {
            return numToRound + multiple - remainder;
        }
    }
}