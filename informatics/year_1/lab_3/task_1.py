import re
from re import Pattern


def gen_pattern(parts: list[str]) -> Pattern:
    escaped = ''
    pattern = re.compile(''.join(parts))
    return pattern


def count(text: str, pattern: Pattern) -> int:
    return len(pattern.findall(text))
