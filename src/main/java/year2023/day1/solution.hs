import System.IO
import Control.Monad
import Data.Char
import Data.List

main :: IO()
main = do
  handle <- openFile "input.txt" ReadMode
  input <- hGetContents handle
  let puzzleInput = lines input
  print (partA puzzleInput)
  print (partB puzzleInput)
  
partA :: [String] -> Int
partA [] = 0
partA (line:input) = ((getFirstNumber line) * 10 + getLastNumber line) + partA input

getFirstNumber :: String -> Int
getFirstNumber (c:line) | isDigit c = digitToInt c
                        | otherwise = getFirstNumber line
                        
getLastNumber :: String -> Int
getLastNumber line | isDigit $ last line = digitToInt $ last line
                   | otherwise = getLastNumber $ init line
                   
digits = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

partB :: [String] -> Int
partB [] = 0
partB (line:input) = ((getFirstNumberPartB line) * 10 + getLastNumberPartB line) + partB input
                   
getFirstNumberPartB :: String -> Int
getFirstNumberPartB line | isDigit $ head line = digitToInt $ head line
                         | index >= 0 = index + 1
                         | otherwise = getFirstNumberPartB $ tail line
                         where index = getMatchingIndex isPrefixOf line digits 0
                         
getLastNumberPartB :: String -> Int
getLastNumberPartB line | isDigit $ last line = digitToInt $ last line
                        | index >= 0 = index + 1
                        | otherwise = getLastNumberPartB $ init line
                        where index = getMatchingIndex isSuffixOf line digits 0
                         
getMatchingIndex function line [] index = -1
getMatchingIndex function line (x:xs) index | function x line = index
                                            | otherwise = getMatchingIndex function line xs (index + 1) 