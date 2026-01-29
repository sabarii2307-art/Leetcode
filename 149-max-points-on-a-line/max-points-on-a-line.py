from collections import defaultdict

class Solution:
    def maxPoints(self, points):
        n = len(points)
        if n <= 2:
            return n

        def gcd(a, b):
            while b:
                a, b = b, a % b
            return a

        result = 0

        for i in range(n):
            slopes = defaultdict(int)
            max_on_line = 0
            x1, y1 = points[i]

            for j in range(i + 1, n):
                x2, y2 = points[j]
                dx = x2 - x1
                dy = y2 - y1

                if dx == 0:
                    slope = ('inf', 0)     # vertical line
                elif dy == 0:
                    slope = (0, 'inf')     # horizontal line
                else:
                    g = gcd(dx, dy)
                    dx //= g
                    dy //= g
                    slope = (dy, dx)

                slopes[slope] += 1
                max_on_line = max(max_on_line, slopes[slope])

            result = max(result, max_on_line + 1)

        return result
