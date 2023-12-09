import System.IO
import Data.List.Split

main :: IO()
main = do
  handle <- openFile "input.txt" ReadMode
  input <- hGetContents handle
  let puzzleInput = map (\line -> readInts line) $ lines input
  print (partA puzzleInput)
  print (partB puzzleInput)

partA :: [[Int]] -> Int
partA [] = 0
partA (x:values) = sumLastElements (getSequence x) + partA values

partB :: [[Int]] -> Int
partB [] = 0
partB (x:values) = subtractFirstElements (getSequence x) + partB values

sumLastElements :: [[Int]] -> Int
sumLastElements [] = 0
sumLastElements (x:values) = last x + (sumLastElements values)

subtractFirstElements :: [[Int]] -> Int
subtractFirstElements [] = 0
subtractFirstElements (x:values) = head x - (subtractFirstElements values)

getSequence :: [Int] -> [[Int]]
getSequence values | all (==0) values = [values]
                   | otherwise = [values] ++ getSequence (getDifferences values)

getDifferences :: [Int] -> [Int]
getDifferences [] = []
getDifferences (x:y:[]) = [y - x]
getDifferences (x:y:values) = [y - x] ++ getDifferences (y:values)

readInts :: String -> [Int]
readInts line = map (\value -> read value :: Int) $ splitOn " " line