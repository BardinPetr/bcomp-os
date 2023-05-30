#!/usr/bin/python

import argparse

from process import *


def main():
  global DEBUG

  parser = argparse.ArgumentParser(
    prog='BComp ASM preprocessor',
    description='Build code in preprocessor with language into Genuine BComp ASM and create linking tables'
  )
  parser.add_argument('entrypoint', help='Main code file path')
  parser.add_argument('output', help='Generated output file path')
  parser.add_argument('-t', '--linking-table', help='File path for linking table of result code exported functions')
  parser.add_argument('-d', '--debug', action='store_true', help='Enable debug mode')

  args = parser.parse_args()
  DEBUG = args.debug

  code = ""
  with open(args.entrypoint, 'r') as f:
    code = f.read()

  code = preprocess_includes(code)

  code, func_table = generate_headers(code)
  code = insert_table(code, func_table)

  code = preprocess_strings(code)
  code = preprocess_stack_names(code)

  with open(args.output, 'w') as f:
    f.write(code)

  if args.linking_table is not None:
    abi = build_abi(code, func_table)

    with open(args.linking_table, 'w') as f:
      f.write(abi)


if __name__ == '__main__':
  main()
