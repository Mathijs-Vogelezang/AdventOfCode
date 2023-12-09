import System.IO
import Data.List (intersect)
import Data.List.Split 

main :: IO()
main = do
  handle <- openFile "input.txt" ReadMode
  input <- hGetContents handle
  let puzzleInput = parse $ lines input
--  print puzzleInput
  print (partA puzzleInput)
  print (partB puzzleInput)

partA :: [([Int], [Int])] -> Int
partA [] = 0
partA ((winningNumbers,numbers):input) | winningCount == 0 = 0 + partA input
                                       | otherwise = 2 ^ (winningCount - 1) + partA input
                                       where winningCount = countWinningNumbers winningNumbers numbers
                                       
partB :: [([Int], [Int])] -> Int
partB cards = sum $ getCopies cards ((take $ length cards) (repeat 1)) 0

getCopies :: [([Int], [Int])] -> [Int] -> Int -> [Int]
getCopies [] copies index = copies
getCopies ((winningNumbers,numbers):cards) copies index = getCopies cards (processCard winningCount copies index) (index + 1)
                                    where winningCount = countWinningNumbers winningNumbers numbers

processCard :: Int -> [Int] -> Int -> [Int]
processCard winningCount copies index | winningCount == 0 = copies
                                      | otherwise = processCard (winningCount - 1) (setElement copies (index + winningCount) (copies!!(index + winningCount) + copies!!index)) index

setElement list index element | index < length list = xs ++ element : ys
                              | otherwise = list
                              where (xs,_:ys) = splitAt index list

countWinningNumbers winningNumbers numbers = length $ intersect winningNumbers numbers 
                   
parse :: [String] -> [([Int], [Int])]
parse [] = []
parse (line:input) = [(partA, partB)] ++ parse input
                   where parts = splitOn "|" $ last $ splitOn ":" line
                         partA = map (\x -> read x :: Int) $ filter (\x -> x /= "") $ splitOn " " $ head parts
                         partB = map (\x -> read x :: Int) $ filter (\x -> x /= "") $ splitOn " " $ last parts