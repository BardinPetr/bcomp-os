import argparse
from random import randint, choices, random

from hamming import decode, encode
from tools import to_bin_list, from_list_to_str


def demo():
    n = randint(1, 30)
    src = choices([0, 1], k=n)
    print(f"DEMO")
    print(f"INPUT (length={n}):", src)
    enc = encode(src)[0]
    print("ENCODED:", enc)
    if 0.5 > random():
        epos = randint(0, n - 1)
        enc[epos] = 1 - enc[epos]
        print("ERROR MADE AT", epos)
    print("RUNNING DECODE\n-------\n")
    res, _, logs = decode(enc, gen_logs=True)
    assert res == src
    print(logs)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Hamming code encoder/decoder')
    group = parser.add_mutually_exclusive_group()
    group.add_argument("--encode", type=to_bin_list)
    group.add_argument("--decode", type=to_bin_list)
    group.add_argument("--demo", action='store_true', dest='demo')
    args = parser.parse_args()

    if args.encode is not None:
        res, full_len, data_len = encode(args.encode)
        print(f"Encoded ({full_len}/{data_len}): {from_list_to_str(res)}")
    elif args.decode is not None:
        _, _, logs = decode(args.decode, gen_logs=True)
        print(logs)
    elif args.demo:
        demo()
    else:
        parser.print_help()
