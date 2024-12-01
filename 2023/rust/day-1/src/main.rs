use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn main() -> io::Result<()> {
    let file = File::open("input/input")?;
    let reader = BufReader::new(file);

    let mut sum: u32 = 0;
    for line in reader.lines() {
        sum += calibration_value(line?).unwrap();
    }

    println!("{}", sum);

    Ok(())
}

fn calibration_value(line: String) -> Option<u32> {
    let left: u32 = first_digit(line.chars())?;
    let right: u32 = first_digit_reversed(line.chars().rev())?;
    return Some((left*10) + right);
}

fn first_digit(iter: impl IntoIterator<Item = char>) -> Option<u32> {
    let digits = vec!["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
    let mut digit_progress = vec![0; 10];

    for char in iter {
        if char.is_digit(10) {
            return char.to_digit(10);
        }
        for i in 1..10 {
            if char == digits[i].chars().nth(digit_progress[i])? {
                digit_progress[i] += 1;
                if digit_progress[i] == digits[i].len() {
                    return Some(i as u32);
                }
            } else {
                digit_progress[i] = (char == digits[i].chars().nth(0)?) as usize;
            }
        }
    }

    return None;
}

fn first_digit_reversed(iter: impl IntoIterator<Item = char>) -> Option<u32> {
    let digits = vec!["", "eno", "owt", "eerht", "ruof", "evif", "xis", "neves", "thgie", "enin"];
    let mut digit_progress = vec![0; 10];

    for char in iter {
        if char.is_digit(10) {
            return char.to_digit(10);
        }
        for i in 1..10 {
            if char == digits[i].chars().nth(digit_progress[i])? {
                digit_progress[i] += 1;
                if digit_progress[i] == digits[i].len() {
                    return Some(i as u32);
                }
            } else {
                digit_progress[i] = (char == digits[i].chars().nth(0)?) as usize;
            }
        }
    }

    return None;
}

