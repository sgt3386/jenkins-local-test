import argparse
import os

parser = argparse.ArgumentParser("writeFile")
parser.add_argument("path", help="The path of the file to be created or written to", type=str)
parser.add_argument("text", help="The text to write to the file", type=str)
args = parser.parse_args()

os.makedirs(os.path.dirname(args.path), exist_ok=True)
with open(args.path, "w") as file:
    file.write(args.text)
