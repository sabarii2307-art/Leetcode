import heapq

class Solution:
    def getSkyline(self, buildings):
        events = []
        
        # Create events: (x, height)
        # Start of building → negative height
        # End of building → positive height
        for left, right, height in buildings:
            events.append((left, -height, right))
            events.append((right, 0, 0))
        
        # Sort events by x, then by height
        events.sort()
        
        result = []
        heap = [(0, float('inf'))]  # (negative height, end position)
        prev_height = 0
        
        for x, neg_height, right in events:
            # Remove buildings that ended
            while heap and heap[0][1] <= x:
                heapq.heappop(heap)
            
            # Add new building
            if neg_height != 0:
                heapq.heappush(heap, (neg_height, right))
            
            # Current max height
            current_height = -heap[0][0]
            
            # If height changes, add key point
            if current_height != prev_height:
                result.append([x, current_height])
                prev_height = current_height
        
        return result